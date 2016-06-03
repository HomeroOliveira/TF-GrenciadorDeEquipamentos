package dao;

import modelo.Equipamento;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class EquipamentoDao extends AbstratcDao<Equipamento> {

    private static final String BUSCAR_TODOS = "SELECT * FROM equipamentos";
    private static final String INSERIR = "INSERT INTO EQUIPAMENTOS " +
            "(COD_EQUIPAMENTO, DATA_AQUISICAO, DESCRICAO, CUSTO_DIARIO," +
            " TIPO_EQUIPAMENTO, EM_MANUTENCAO) " +
            " VALUES (?,?,?,?,?,?)";
    private static final String BUSCAR_DESCRICAO = "select * from Equipamentos where descricao = ?";


    public Optional<Equipamento> buscarPor(String descricao) {

        try (Connection connection = DataBase.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(BUSCAR_DESCRICAO)) {
                statement.setString(1, descricao);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Equipamento equipamento = criar(resultSet);
                        return Optional.of(equipamento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    protected Equipamento criar(ResultSet resultSet) throws SQLException {
        int codEquipamento = resultSet.getInt(1);
        LocalDate dataAquisicao = resultSet.getDate(2).toLocalDate();
        String descricao = resultSet.getString(3);
        BigDecimal custoDiario = resultSet.getBigDecimal(4);
        String tipoEquipamento = resultSet.getString(5);
        boolean emManutencao = resultSet.getString(6).equals("S");

        return new Equipamento(codEquipamento, dataAquisicao,
                descricao, custoDiario, tipoEquipamento, emManutencao);
    }


    public void inserir(Equipamento equipamento) throws SQLException {
        try (Connection connection = DataBase.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERIR)) {
                preparedStatement.setInt(1, equipamento.getCodEquipamento());
                preparedStatement.setDate(2, Date.valueOf(equipamento.getDataAquisao()));
                preparedStatement.setString(3, equipamento.getDescricao());
                preparedStatement.setBigDecimal(4, equipamento.getCustoDiario());
                preparedStatement.setString(5, equipamento.getTipoEquipamento());
                String emManutencao = equipamento.isEmManutencao()?"S":"N";
                preparedStatement.setString(6, emManutencao);
                preparedStatement.execute();
            }
        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }

    }


    @Override
    protected String getBUSCAR_TODOS() {
        return BUSCAR_TODOS;
    }

    public Equipamento buscarPorCod(int codEquipamento) {
        return null;
    }
}
