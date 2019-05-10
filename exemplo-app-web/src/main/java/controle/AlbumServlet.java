package controle;

import modelo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/albuns")
public class AlbumServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    	//todo: fazer listagem de álbuns
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    	String titulo = req.getParameter("titulo");
    	String autor = req.getParameter("autor");
    	boolean publicado = Boolean.parseBoolean(req.getParameter("publicado"));

    	Album album = new Album(titulo, autor, publicado);

    	//todo: fazer upload de arquivo e atribuí-lo ao album


		AlbumDAO albumDAO = AlbumDAOClasse.getInstance();
		albumDAO.criarAlbum(album);

		try {
			req.setAttribute("albuns", albumDAO.listarAlbuns());
			req.getRequestDispatcher("albuns.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
