package br.edu.ifpb.pweb1.exemplo_sessao;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.addHeader("Content-type", "text/html");
			response.getOutputStream().println("<h1>Área restrita</h1>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
