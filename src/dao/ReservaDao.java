package dao;

import modelo.Equipamento;
import modelo.Funcionario;
import modelo.Reserva;
import utils.LocalDateUtils;
import utils.Tuple;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReservaDao {

    private static final String BUSCAR_TODOS = "SELECT  r.cod_reserva,r.cod_equipamento, r.cod_matricula " +
            ",f.NOME, e.descricao ,r.DATA_INICIAL, r.DATA_FINAL FROM reservas r" +
            "  JOIN FUNCIONARIOS f ON r.COD_MATRICULA = f.COD_MATRICULA" +
            "  JOIN Equipamentos e ON r.cod_equipamento = e.cod_equipamento";

    private static final String INSERIR = "INSERT INTO RESERVAS (COD_EQUIPAMENTO, " +
            "COD_MATRICULA, DATA_INICIAL, DATA_FINAL) VALUES (?,?,?,?)";

    private static final String RESERVAS_FUTURAS = "SELECT  r.cod_reserva,r.cod_equipamento, r.cod_matricula " +
            ", f.nome, e.descricao, r.data_final, r.data_final FROM RESERVAS r " +
            " JOIN FUNCIONARIOS f ON r.COD_MATRICULA = f.COD_MATRICULA" +
            " JOIN EQUIPAMENTOS e ON r.COD_EQUIPAMENTO = e.COD_EQUIPAMENTO"+
            " WHERE r.DATA_INICIAL > sysdate";


    public List<Reserva> buscarTodos() {
        List<Reserva> reservas = new ArrayList<>();

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(BUSCAR_TODOS)) {
                    criaReservas(reservas, resultSet);

                }
            }
        } catch (SQLException e) {
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
            if (equipamento == null) {
                String descricao = resultSet.getString(5);
                equipamento = new Equipamento(codEquipamento, descricao);
                porCodEquipamento.put(codEquipamento, equipamento);
            }

            Funcionario funcionario = porCodMatricula.get(codMatricula);
            if (funcionario == null) {
                String nome = resultSet.getString(4);
                funcionario = new Funcionario(codMatricula, nome);
                porCodMatricula.put(codEquipamento, funcionario);
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
            throw new Exception(e.getMessage());
        }


    }


    public List<Reserva> buscarReservasFuturas() {
        List<Reserva> reservas = new ArrayList<>();

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(RESERVAS_FUTURAS)) {
                    criaReservas(reservas, resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;

    }


    public List<Tuple<String, Integer, BigDecimal>> buscarQuantidadeDeReservasComEquipamentos() {
        List<Tuple<String, Integer, BigDecimal>> resultado = new ArrayList<>();
        String sql = "SELECT e.DESCRICAO, count(*) AS quantidades_reservas, " +
                "sum(e.CUSTO_DIARIO * (r.data_final - r.data_inicial + 1)) AS soma_custo_diario " +
                "FROM RESERVAS r " +
                "JOIN EQUIPAMENTOS e ON r.COD_EQUIPAMENTO = e.COD_EQUIPAMENTO " +
                "GROUP BY e.DESCRICAO";

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        String descricao = resultSet.getString(1);
                        int quantidadeReservas = resultSet.getInt(2);
                        BigDecimal somaCustoDiario = resultSet.getBigDecimal(3);
                        Tuple<String, Integer, BigDecimal> tupla =
                                new Tuple<>(descricao, quantidadeReservas, somaCustoDiario);
                        resultado.add(tupla);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }





    public List<Tuple<String, Integer, BigDecimal>> buscarQuantidadeDeReservasComFuncionario() {
        List<Tuple<String, Integer, BigDecimal>> resultado = new ArrayList<>();
        String sql  = "SELECT f.NOME, count(f.COD_MATRICULA), sum(e.CUSTO_DIARIO * (r.data_final - r.data_inicial + 1)) AS soma " +
                "FROM FUNCIONARIOS f " +
                "  JOIN RESERVAS r ON f.COD_MATRICULA = r.COD_MATRICULA" +
                "  JOIN EQUIPAMENTOS e ON r.COD_EQUIPAMENTO = e.COD_EQUIPAMENTO " +
                "GROUP BY f.NOME, e.DESCRICAO " +
                "ORDER BY soma DESC ";

        try (Connection connection = DataBase.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        String nome = resultSet.getString(1);
                        int quantidadeReservas = resultSet.getInt(2);
                        BigDecimal somaCustoDiario = resultSet.getBigDecimal(3);
                        Tuple<String, Integer, BigDecimal> tupla =
                                new Tuple<>(nome, quantidadeReservas, somaCustoDiario);
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
