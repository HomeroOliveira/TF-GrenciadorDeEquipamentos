package dao;

import modelo.Funcionario;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class FuncionarioDao extends AbstratcDao<Funcionario> {

    private static final String BUSCAR_TODOS = "SELECT * FROM funcionarios ORDER BY nome";
    private static final String BUSCAR_POR_NOME = "SELECT * FROM funcionarios WHERE nome = ?";
    private static final String INSERIR = "INSERT INTO Funcionarios( " +
            "cod_matricula, nome, senha, data_nascimento, " +
            "data_admissao, sexo, endereco, salario_mensal) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static int COD_MATRICULA = 1;


    public Optional<Funcionario> buscarPor(String nome) {

        try (Connection conn = DataBase.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_NOME)) {
                stmt.setString(3, nome);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    if (resultSet.next()) {
                        Funcionario funcionario = criar(resultSet);
                        return Optional.of(funcionario);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public int inserir(Funcionario funcionario) {
        try (Connection conn = DataBase.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(INSERIR)) {
                stmt.setInt(COD_MATRICULA, funcionario.getCodMatricula());
                stmt.setString(2, funcionario.getNome());
                stmt.setString(3, funcionario.getSenha());
                stmt.setDate(4, Date.valueOf(funcionario.getDataNascimento()));
                stmt.setDate(5, Date.valueOf(funcionario.getDataAdmissao()));
                stmt.setString(6, funcionario.getSexo());
                stmt.setString(7, funcionario.getEndereco());
                stmt.setBigDecimal(8, funcionario.getSalario());

                return stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir funcionario "
                    + e.getMessage());
        }
    }

    @Override
    protected String getBUSCAR_TODOS() {
        return BUSCAR_TODOS;
    }

    @Override
    protected Funcionario criar(ResultSet resultSet) throws SQLException {
        int codMatricula = resultSet.getInt(COD_MATRICULA);
        String nome = resultSet.getString(3);
        String senha = resultSet.getString(2);
        LocalDate dataNascimento = resultSet.getDate(4).toLocalDate();
        LocalDate dataAdmissao = resultSet.getDate(5).toLocalDate();
        String sexo = resultSet.getString(6);
        String endereco = resultSet.getString(7);
        BigDecimal salario = resultSet.getBigDecimal(8);

        return new Funcionario(codMatricula, nome, senha, dataNascimento,
                dataAdmissao, sexo, endereco, salario);

    }
}
