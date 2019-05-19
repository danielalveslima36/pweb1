import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Servlet1")
public class Servlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        Integer valorAtual = (Integer) req.getServletContext().getAttribute("totalReq");
        String max = req.getServletContext().getInitParameter("total");

        if(valorAtual < Integer.parseInt(max)){
            valorAtual = new Integer(valorAtual + 1);
            req.getServletContext().setAttribute("totalReq", valorAtual);

            String texto = "Requisição: " + valorAtual;
             req.setAttribute("texto", texto);
            try {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            String texto = "Você chegou no limite de excessões";
            req.setAttribute("texto", texto);
            try {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
