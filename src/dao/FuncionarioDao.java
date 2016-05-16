package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.Funcionario;

public class FuncionarioDao {

	private static final String BUSCAR_TODOS = "select * from funcionarios order by nome";
	private static final String BUSCAR_POR_NOME = "select * from funcionarios where nome = ?";
	private static final String INSERIR = new StringBuilder()
			.append("INSERT INTO Funcionarios(")
			.append("cod_matricula, senha, nome, data_nascimento, ")
			.append("data_admissao, sexo, endereco, salario_mensal) ")
			.append("VALUES (?,?,?,?,?,?,?,?);").toString();

	public List<Funcionario> buscarTodos() {
		List<Funcionario> funcionarios = new ArrayList<>();

		try (Connection conn = DataBase.getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				try (ResultSet resultSet = stmt.executeQuery(BUSCAR_TODOS)) {
					while (resultSet.next()) {
						Funcionario funcionario = criar(resultSet);
						funcionarios.add(funcionario);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionarios;
	}

	public Optional<Funcionario> buscarPorNome(String nome) {

		try (Connection conn = DataBase.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(BUSCAR_POR_NOME)) {
				stmt.setString(2, nome);
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

	public int inserir(Funcionario funcionario){
		try (Connection conn = DataBase.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(INSERIR)){
				stmt.setInt(1, funcionario.getCodMatricula());
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

	private Funcionario criar(ResultSet resultSet) {
		try {
			int codMatricula = resultSet.getInt(1);
			String nome = resultSet.getString(2);
			String senha = resultSet.getString(3);
			LocalDate dataNascimento = resultSet.getDate(4).toLocalDate();
			LocalDate dataAdmissao = resultSet.getDate(5).toLocalDate();
			String sexo = resultSet.getString(6);
			String endereco = resultSet.getString(7);
			BigDecimal salario = resultSet.getBigDecimal(8);
			return new Funcionario(codMatricula, nome, senha, dataNascimento, dataAdmissao, sexo, endereco, salario);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao recuperar usuario " 
		                               + e.getMessage());
		}
	}
}
