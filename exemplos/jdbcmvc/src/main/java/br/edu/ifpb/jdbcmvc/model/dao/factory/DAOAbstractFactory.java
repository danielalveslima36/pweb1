	package br.edu.ifpb.jdbcmvc.model.dao.factory;

import br.edu.ifpb.jdbcmvc.model.dao.AlunoDAO;

public interface DAOAbstractFactory {
	
	public AlunoDAO criaAlunoDAO();
	
}
