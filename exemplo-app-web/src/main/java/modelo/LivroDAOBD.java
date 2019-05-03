package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.conexao.ConnectionFactory;

public class LivroDAOBD implements LivroDAO {

	private List<Livro> livros = new ArrayList();
	
	private Connection conexao;
	
	public LivroDAOBD() {
		ConnectionFactory factory = new ConnectionFactory();
		this.conexao = factory.getConnection();
	}
	
	public List<Livro> listarLivros() {
		List<Livro> livros = new ArrayList();
		try {
			Statement statement = this.conexao.createStatement();
			statement.execute("SELECT * FROM livros");
			ResultSet resultSet = statement.getResultSet();
			
			while(resultSet.next()) {
				livros.add(new Livro(resultSet.getInt("id"), resultSet.getString("titulo"), resultSet.getString("isbn"), resultSet.getString("autor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}

	public Optional<Livro> recuperarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Livro criarLivro(Livro livro) {
		// TODO Auto-generated method stub
		return null;
	}

	public Livro atualizarLivro(Livro livro) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removerLivro(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}
