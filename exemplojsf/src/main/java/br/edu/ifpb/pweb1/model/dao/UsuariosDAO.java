package br.edu.ifpb.pweb1.model.dao;

import java.util.Optional;

import br.edu.ifpb.pweb1.model.dao.generic.GenericDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

public class UsuariosDAO extends GenericDAO<Usuario>{
	
	public Optional<Usuario> findByLogin(String login) {
		return this.findByField("login", login);
	}
	
}
