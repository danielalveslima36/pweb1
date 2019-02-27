package br.edu.ifpb.pweb1.model.dao;

import java.util.List;
import java.util.Optional;

import br.edu.ifpb.pweb1.model.domain.Usuario;

public interface UsuarioDAO {

	public List<Usuario> listar() throws DataAccessException;
	
	public Optional<Usuario> recuperar(String login) throws DataAccessException;
	
	public void salvar(Usuario usuario) throws DataAccessException;
	
	public void remover(String login) throws DataAccessException;
	
	public Long contar() throws DataAccessException;
	
}
