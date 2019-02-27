package br.edu.ifpb.pweb1.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.model.jdbc.ConnectionException;

@WebServlet("/api")
public class FrontController extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String commandName = request.getParameter("comando");
		try {
			Command command = (Command) Class.forName(this.getClass().getPackage().getName() + "."+commandName).newInstance();
			command.execute(request, response);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			response.sendError(404);
		} catch (CommandException e) {
			response.sendError(e.getStatuscode(), e.getMessage());
		} catch (ConnectionException e) {
			response.sendError(500, e.getMessage());
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
	
}
