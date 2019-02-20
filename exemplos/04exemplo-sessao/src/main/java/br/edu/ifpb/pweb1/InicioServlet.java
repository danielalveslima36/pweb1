package br.edu.ifpb.pweb1;


import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String usuarioLogado = (String)request.getSession(true).getAttribute("usuarioLogado");
			
			if (usuarioLogado == null) {
				response.sendRedirect("login.jsp");
			}
			
			response.addHeader("Content-type", "text/html");
			response.getOutputStream().println("<h1>Área restrita</h1>");
			response.getOutputStream().println("<h2>Seja bem-vindo, "+usuarioLogado+" </h2>");			
			response.getOutputStream().println("<a href='logout'>Fazer logout</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
