package br.edu.ifpb.pweb1;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String loginPadrao = request.getServletContext().getInitParameter("loginpadrao");
		String senhaPadrao = request.getServletContext().getInitParameter("senhapadrao");
		
		String loginFornecido = request.getParameter("login");
		String senhaFornecida = request.getParameter("senha");
		
		if (loginFornecido.equals(loginPadrao) && senhaFornecida.equals(senhaPadrao)) {
			// usuario está logado
			request.getSession().setAttribute("usuarioLogado", loginFornecido);
			response.sendRedirect("inicio");
		} else {
			response.sendRedirect("login.jsp");
		}
		
		
	}
	
}
