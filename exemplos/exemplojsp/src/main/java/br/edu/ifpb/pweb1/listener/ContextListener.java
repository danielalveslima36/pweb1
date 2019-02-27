package br.edu.ifpb.pweb1.listener;

import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.ifpb.pweb1.model.dao.UsuariosDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		if (usuarioDAO.count() == 0) {
			Usuario usuario = new Usuario();
			usuario.setDataCadastro(Timestamp.from(Instant.now()));
			usuario.setAtivo(true);
			usuario.setNome(sce.getServletContext().getInitParameter("admnome"));
			usuario.setLogin(sce.getServletContext().getInitParameter("admlogin"));
			usuario.setSenha(sce.getServletContext().getInitParameter("admsenha"));
			usuarioDAO.save(usuario);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
