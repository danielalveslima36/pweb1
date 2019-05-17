package controle;

import modelo.Curso;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String curso = req.getServletContext().getInitParameter("curso");
        Curso cursoObj = (Curso) req.getServletContext().getAttribute("curso");
        resp.getOutputStream().println(curso);
        resp.getOutputStream().println("\n");
        resp.getOutputStream().println(cursoObj.getNome());
    }

}
