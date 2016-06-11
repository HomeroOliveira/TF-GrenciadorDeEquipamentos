package dao;

import modelo.Equipamento;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDao extends AbstratcDao<Equipamento> {

    private static final String BUSCAR_TODOS = "SELECT * FROM equipamentos";

    private static final String INSERIR = "INSERT INTO EQUIPAMENTOS " +
            "(COD_EQUIPAMENTO, DATA_AQUISICAO, DESCRICAO, CUSTO_DIARIO," +
            " TIPO_EQUIPAMENTO, EM_MANUTENCAO) " +
            " VALUES (?,?,?,?,?,?)";

    private static final String BUSCAR_DESCRICAO = "SELECT * FROM Equipamentos WHERE DESCRICAO LIKE ?";

    private static final String COD_EQUIMENTO = "COD_EQUIPAMENTO";
    private static final String DATA_AQUISICAO = "DATA_AQUISICAO";
    private static final String DESCRICAO = "DESCRICAO";
    private static final String CUSTO_DIARIO = "CUSTO_DIARIO";
    private static final String TIPO_EQUIPAMENTO = "TIPO_EQUIPAMENTO";
    private static final String EM_MANUTENCAO = "EM_MANUTENCAO";

    public List<Equipamento> buscarPorDescricao(String descricao) {

        List<Equipamento> equipamentos = new ArrayList<>();
    	
        try (Connection connection = DataBase.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(BUSCAR_DESCRICAO)) {
                statement.setString(1, "%"+descricao+"%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Equipamento equipamento = criar(resultSet);
                        equipamentos.add(equipamento);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamentos;
    }


    protected Equipamento criar(ResultSet resultSet) throws SQLException {
        int codEquipamento = resultSet.getInt(COD_EQUIMENTO);
        LocalDate dataAquisicao = resultSet.getDate(DATA_AQUISICAO).toLocalDate();
        String descricao = resultSet.getString(DESCRICAO);
        BigDecimal custoDiario = resultSet.getBigDecimal(CUSTO_DIARIO);
        String tipoEquipamento = resultSet.getString(TIPO_EQUIPAMENTO);
        boolean emManutencao = resultSet.getString(EM_MANUTENCAO).equals("S");

        return new Equipamento(codEquipamento, dataAquisicao,
                descricao, custoDiario, tipoEquipamento, emManutencao);
    }


    public void inserir(Equipamento equipamento)  {
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
        catch (SQLException e) {
           e.printStackTrace();
       }

    }


	@Override
	protected String getBUSCAR_TODOS() {
		return BUSCAR_TODOS;
	}


}
