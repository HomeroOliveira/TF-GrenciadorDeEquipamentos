package dao;

import modelo.Equipamento;
import modelo.Funcionario;
import modelo.Reserva;
import utils.LocalDateUtils;
import utils.Tuple;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReservaDao {

    private static final String BUSCAR_TODOS = "SELECT  r.cod_reserva,r.cod_equipamento, r.cod_matricula " +
            ",f.NOME, e.descricao ,r.DATA_INICIAL, r.DATA_FINAL from reservas r" +
            "  join FUNCIONARIOS f on r.COD_MATRICULA = f.COD_MATRICULA" +
            "  join Equipamentos e on Reservas.cod_equipamento = e.cod_equipamento";
    private static final String INSERIR = "INSERT INTO RESERVAS (COD_EQUIPAMENTO, " +
            "COD_MATRICULA, DATA_INICIAL, DATA_FINAL) VALUES (?,?,?,?)";
    private static final String sql = "select FUNCIONARIOS.NOME, reservas.DATA_INICIAL, reservas.DATA_FINAL from reservas " +
    		"join FUNCIONARIOS on reservas.COD_MATRICULA = FUNCIONARIOS.COD_MATRICULA";

    public List<Reserva> buscarTodos() {
        List<Reserva> reservas = new ArrayList<>();


        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(BUSCAR_TODOS)) {
                    criaReservas(reservas, resultSet);

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;

    }

    private void criaReservas(List<Reserva> reservas, ResultSet resultSet) throws SQLException {
        Map<Integer, Equipamento> porCodEquipamento = new HashMap<>();
        Map<Integer, Funcionario> porCodMatricula = new HashMap<>();
        while (resultSet.next()) {
            int codReserva = resultSet.getInt(1);
            int codEquipamento = resultSet.getInt(2);
            int codMatricula = resultSet.getInt(3);
            LocalDate dataInicial = LocalDateUtils.toLocalDate(resultSet.getDate(6));
            LocalDate dataFinal = LocalDateUtils.toLocalDate(resultSet.getDate(7));

            Equipamento equipamento = porCodEquipamento.get(codEquipamento);
            if(equipamento != null){
                String descricao = resultSet.getString(5);
                equipamento = new Equipamento(codEquipamento, descricao);
                porCodEquipamento.put(codEquipamento, equipamento);
            }

            Funcionario funcionario = porCodMatricula.get(codMatricula);
            if(funcionario != null){
                String nome = resultSet.getString(4);
                funcionario = new Funcionario(codMatricula, nome);
                porCodEquipamento.put(codEquipamento, equipamento);
            }

            Reserva reserva = new Reserva(codReserva, equipamento, funcionario, dataInicial, dataFinal);
            reservas.add(reserva);
        }
    }


    public int inserir(Reserva reserva) throws Exception {
        try (Connection connection = DataBase.getConnection()) {
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement(INSERIR)) {
                preparedStatement.setInt(1, reserva.getCodEquipamento());
                preparedStatement.setInt(2, reserva.getCodMatricula());
                preparedStatement.setDate(3, Date.valueOf(reserva.getDataInicial()));
                preparedStatement.setDate(4, Date.valueOf(reserva.getDataFinal()));
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw   new Exception(e.getMessage());
        }


    }






    public List<Tuple<String, LocalDate, LocalDate>> buscarReservasFuturas() {
        List<Tuple<String, LocalDate, LocalDate>> resultado = new ArrayList<>();

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while(resultSet.next()){
                    String funcionario = resultSet.getString(1);
                    LocalDate dataInicial = resultSet.getDate(2).toLocalDate();
                    LocalDate dataFinal = resultSet.getDate(3).toLocalDate();
                    Tuple<String, LocalDate, LocalDate> tupla = new Tuple<>(funcionario,
                            dataInicial, dataFinal);
                    resultado.add(tupla);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;

    }






}
