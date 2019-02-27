package br.edu.ifpb.pweb1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import br.edu.ifpb.pweb1.model.domain.Usuario;
import br.edu.ifpb.pweb1.model.jdbc.ConnectionFactory;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection connection;
	
	public UsuarioDAOImpl() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	private static Logger logger = Logger.getLogger(UsuarioDAOImpl.class.getName());
	
	public List<Usuario> listar() throws DataAccessException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM usuario";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				usuarios.add(new Usuario(resultSet.getLong("id"), resultSet.getString("login"), resultSet.getString("nome"), resultSet.getString("senha"), resultSet.getTimestamp("datacriacao"), resultSet.getBoolean("ativo")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao executar busca por usuário");
		}
		return usuarios;
	}
	
	public Long contar() throws DataAccessException {
		Long usuarios = 0l;
		try {
			ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM usuario");
			if (resultSet.next()) {
				usuarios = resultSet.getLong(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao ler quantidade de usuários");
		}
		return usuarios;
	}

	public Optional<Usuario> recuperar(String login) throws DataAccessException {
		Optional<Usuario> usuario = Optional.empty();
		try {
			String query = "SELECT * FROM usuario WHERE login = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				usuario = Optional.of(new Usuario(resultSet.getLong("id"), resultSet.getString("login"), resultSet.getString("nome"), resultSet.getString("senha"), resultSet.getTimestamp("datacriacao"), resultSet.getBoolean("ativo")));
			}
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao recuperar usuário por login");
		}
		return usuario;
	}

	public void salvar(Usuario usuario) throws DataAccessException {
		try {
			boolean usuarioExistente = this.recuperar(usuario.getLogin()).isPresent()?true:false;
			String query = "";
			if (!usuarioExistente) {
				query = "INSERT INTO usuario (login, nome, senha, datacriacao, ativo) VALUES(?,?,?,?,?)";
			} else {
				query = "UPDATE usuario SET login = ?, nome = ?, senha = ?, datacriacao = ?, ativo = ? WHERE login = ?";
			}
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getSenha());
			statement.setTimestamp(4, usuario.getDataCadastro());
			statement.setBoolean(5,  usuario.isAtivo());
			if (usuarioExistente) {
				statement.setString(6,  usuario.getLogin());
			}
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Falha ao salvar usuário");
		}
	}

	public void remover(String login) throws DataAccessException {
		try {
			String query = "DELETE FROM usuario WHERE login = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, login);
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new DataAccessException("Falha ao remover usuário");
		}
	}

	

}
