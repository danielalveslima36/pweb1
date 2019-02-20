package br.edu.ifpb.jdbcmvc.listener;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.ifpb.jdbcmvc.model.dao.AlunoDAO;
import br.edu.ifpb.jdbcmvc.model.dao.DataAccessException;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOAbstractFactory;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOFactory;
import br.edu.ifpb.jdbcmvc.model.dao.factory.DAOFactory.FactoryType;
import br.edu.ifpb.jdbcmvc.model.domain.Aluno;
import br.edu.ifpb.jdbcmvc.model.jdbc.ConnectionFactory;

@WebListener
public class InitListener implements ServletContextListener {

	private Logger log = Logger.getLogger(InitListener.class.getName());
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Connection connection = ConnectionFactory.getInstance().getConnection();
		sce.getServletContext().setAttribute("conexao", connection);
		String disciplina = sce.getServletContext().getInitParameter("disciplina");
		String curso = sce.getServletContext().getInitParameter("curso");
		System.out.println("Disciplina = "+ disciplina );
		System.out.println("Curso = "+ curso );
		log.info("APLICAÇÃO SUBIU");
		DAOAbstractFactory daoFactory = DAOFactory.createFactory(FactoryType.JDBC);
		AlunoDAO alunoDAO = daoFactory.criaAlunoDAO();
		try {
			if (alunoDAO.contarAlunos() == 0) {
				Aluno aluno = new Aluno("123454254", "José da Silva", 17);
				alunoDAO.salvarAluno(aluno);
			}
		} catch (DataAccessException e) {
			log.severe("Falha ao salvar aluno");
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("APLICAÇÃO FOI DESTRUÍDA");
		DAOAbstractFactory daoFactory = DAOFactory.createFactory(FactoryType.JDBC);
		AlunoDAO alunoDAO = daoFactory.criaAlunoDAO();
		try {
			alunoDAO.removerAluno("123454254");
		} catch (DataAccessException e) {
			log.severe("Falha ao remover aluno");
		}
	}

}
