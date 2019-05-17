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
        //Parâmetro de Inicialização (Só pode ser criado via configuração na inicialização da aplicação e não pode ser alterado/removido)
        String curso = req.getServletContext().getInitParameter("curso");

        //Atributo de Contexto (pode ser alterado/removido e criado em qualquer tempo durante a execução da aplicação)
        Curso cursoObj = (Curso) req.getServletContext().getAttribute("curso");
        resp.getOutputStream().println(curso);
        resp.getOutputStream().println("\n");
        resp.getOutputStream().println(cursoObj.getNome());
    }

}
