package br.edu.ifpb.pweb1.listener;

import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	private static Logger log = Logger.getLogger("SessionListener");
		
	public void sessionCreated(HttpSessionEvent se) {
		Object online = se.getSession().getServletContext().getAttribute("online");
		if (online != null) {
			int usuariosOnline = (Integer) online;
			se.getSession().getServletContext().setAttribute("online", ++usuariosOnline);
		} else {
			se.getSession().getServletContext().setAttribute("online", 1);
		}
		
		// TODO Auto-generated method stub
		log.info("Sessao criada com o id = "+ se.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		Object online = se.getSession().getServletContext().getAttribute("online");
		if (online != null) {
			int usuariosOnline = (Integer) online;
			se.getSession().getServletContext().setAttribute("online", --usuariosOnline);
		}
		
		log.info("sessao destruída, id = "+ se.getSession().getId());
	}

}
