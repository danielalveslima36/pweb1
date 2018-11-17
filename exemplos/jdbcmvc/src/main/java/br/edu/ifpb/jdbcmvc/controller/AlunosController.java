package br.edu.ifpb.jdbcmvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.jdbcmvc.model.dao.AlunoDAO;
import br.edu.ifpb.jdbcmvc.model.dao.DataAccessException;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOAbstractFactory;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOFactory;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOFactory.FactoryType;

public class AlunosController implements Command {

	private AlunoDAO alunoDAO = null;
	
	public AlunosController() {
		DAOAbstractFactory daoFactory = DAOFactory.createFactory(FactoryType.JDBC);
		alunoDAO = daoFactory.criaAlunoDAO();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			request.setAttribute("alunos", alunoDAO.listarAlunos());
			request.getRequestDispatcher("alunos.jsp").forward(request, response);
		} catch (DataAccessException | ServletException | IOException e) {
			throw new CommandException(500, e.getMessage());
		}
		
	}

}
