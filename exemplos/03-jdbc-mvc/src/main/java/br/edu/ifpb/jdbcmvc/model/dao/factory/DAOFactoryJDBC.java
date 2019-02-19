package br.edu.ifpb.jdbcmvc.model.dao.factory;

import br.edu.ifpb.jdbcmvc.model.dao.AlunoDAO;
import br.edu.ifpb.jdbcmvc.model.dao.AlunoDAOImpl;

public class DAOFactoryJDBC implements DAOAbstractFactory {

	public AlunoDAO criaAlunoDAO() {
		return new AlunoDAOImpl();
	}

}
