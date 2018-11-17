package br.edu.ifpb.jdbcmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import br.edu.ifpb.jdbcmvc.model.domain.Aluno;
import br.edu.ifpb.jdbcmvc.model.jdbc.ConnectionFactory;

public class AlunoDAOImpl implements AlunoDAO {

	private Connection connection;
	
	public AlunoDAOImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	private static Logger logger = Logger.getLogger(AlunoDAOImpl.class.getName());
	
	public List<Aluno> listarAlunos() throws DataAccessException {
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM alunos";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				alunos.add(new Aluno(resultSet.getString("matricula"), resultSet.getString("nome"), resultSet.getInt("idade")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao executar busca por alunos");
		}
		return alunos;
	}
	
	public Long contarAlunos() throws DataAccessException {
		Long alunos = 0l;
		try {
			ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM alunos");
			if (resultSet.next()) {
				alunos = resultSet.getLong(1);
			}
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao ler quantidade de alunos");
		}
		return alunos;
	}

	public Optional<Aluno> recuperarAluno(String matricula) throws DataAccessException {
		Optional<Aluno> aluno = Optional.empty();
		try {
			String query = "SELECT * FROM alunos WHERE matricula = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricula);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				aluno = Optional.of(new Aluno(resultSet.getString("matricula"), resultSet.getString("nome"), resultSet.getInt("idade")));
			}
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao recuperar aluno por matrícula");
		}
		return aluno;
	}

	public void salvarAluno(Aluno aluno) throws DataAccessException {
		try {
			boolean alunoExistente = this.recuperarAluno(aluno.getMatricula()).isPresent()?true:false;
			String query = "";
			if (!alunoExistente) {
				query = "INSERT INTO alunos (matricula, nome, idade) VALUES(?,?,?)";
			} else {
				query = "UPDATE alunos SET matricula = ?, nome = ?, idade = ? WHERE matricula = ?";
			}
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setInt(3, aluno.getIdade());
			if (alunoExistente) {
				statement.setString(4, aluno.getMatricula());
			}
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao salvar aluno");
		}
	}

	public void removerAluno(String matricula) throws DataAccessException {
		try {
			String query = "DELETE FROM alunos WHERE matricula = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, matricula);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao remover aluno");
		}
	}

	

}
