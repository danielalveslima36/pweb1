package br.edu.ifpb.pweb1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.model.dao.DataAccessException;
import br.edu.ifpb.pweb1.model.dao.UsuariosDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

public class UsuariosController implements Command {

	private UsuariosDAO usuarioDAO = null;
	
	public UsuariosController() {
		usuarioDAO = new UsuariosDAO();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String acao = request.getParameter("acao");
			switch(acao) {
				case "login":
				Usuario usuario = usuarioDAO.findByLogin(request.getParameter("login")).orElseThrow( () -> new CommandException(401, "usuário ou senha inválidos"));
				if (usuario.getSenha().equals(request.getParameter("senha")) && usuario.isAtivo()) {
					//Usuario autenticado!
					usuario.setSenha(null);
					request.getSession(true).setAttribute("usuarioLogado", usuario);
					response.sendRedirect("./index.jsp");
				} else {
					 throw new CommandException(401, "usuário ou senha inválidos");
				}
				break;
				case "logout":
					request.getSession().invalidate();
					response.sendRedirect("./login.jsp");
			}
			
		} catch (IOException e) {
			throw new CommandException(500, e.getMessage());
		}
		
	}

}
