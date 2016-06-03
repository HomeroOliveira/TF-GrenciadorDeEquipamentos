package dao;

import modelo.Reserva;
import utils.Tuple;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao extends AbstratcDao<Reserva> {

    private static final String BUSCAR_TODOS = "SELECT * FROM reservas";
    private static final String INSERIR = "INSERT INTO RESERVAS (COD_EQUIPAMENTO, " +
            "COD_MATRICULA, DATA_INICIAL, DATA_FINAL) VALUES (?,?,?,?)";
    private static final String sql = "select FUNCIONARIOS.NOME, reservas.DATA_INICIAL, reservas.DATA_FINAL from reservas " +
    		"join FUNCIONARIOS on reservas.COD_MATRICULA = FUNCIONARIOS.COD_MATRICULA";

    public int inserir(Reserva reserva) {
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
            e.printStackTrace();
        }

        return hashCode();
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



    @Override
    protected String getBUSCAR_TODOS() {
        return BUSCAR_TODOS;
    }

    @Override
    protected Reserva criar(ResultSet resultSet) {
        try {
            int codEquimento = resultSet.getInt(1);
            int codMatricula = resultSet.getInt(2);
            LocalDate dataInicial = resultSet.getDate(3).toLocalDate();
            LocalDate dataFinal = resultSet.getDate(4).toLocalDate();
            return new Reserva(codEquimento, codMatricula, dataInicial, dataFinal);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
