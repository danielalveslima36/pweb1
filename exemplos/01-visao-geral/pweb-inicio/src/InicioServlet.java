import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String nome = request.getParameter("nome");
		
		request.setAttribute("nome", nome);
		
		request.getRequestDispatcher("nome.jsp").forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String nome = request.getParameter("nome");
		
		response.addHeader("Content-type", "text/html");
		
		response.getWriter().append("Recebido via POST");
		response.getWriter().append("<h1>Olá "+nome+"</h1>");
		
	}
	
}
