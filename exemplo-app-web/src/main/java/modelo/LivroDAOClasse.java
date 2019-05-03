package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.conexao.ConnectionFactory;

public class LivroDAOClasse implements LivroDAO {

	private List<Livro> livros = new ArrayList();
	
	public LivroDAOClasse() {
		livros.add(new Livro(1, "Livro 1", "ABC", "José da Silva"));
		livros.add(new Livro(2, "Anjos e demônios", "SDASDASDS", "Dan Brown"));
		livros.add(new Livro(3, "Livro 3", "ABC", "José de Almeida"));
	}
	
	public List<Livro> listarLivros() {
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
