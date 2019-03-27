package br.edu.ifpb.pweb1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.model.dao.ProdutosDAO;
import br.edu.ifpb.pweb1.model.domain.Produto;

@WebServlet(urlPatterns="/imagem")
public class ImagemServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProdutosDAO produtosDAO = new ProdutosDAO();
		
		Long idProduto = Long.parseLong(request.getParameter("idProduto"));
		Produto produto = produtosDAO.findById(idProduto).orElseThrow( () -> new ServletException("Produto não encontrado"));
		
		String imgPath = "/Users/diegopessoa/imagens/";
		
		File imagem = new File(imgPath + produto.getImagem());
		if (imagem.exists()) {
			response.setContentType("image/jpeg");
			response.getOutputStream().write(Files.readAllBytes(imagem.toPath()));	
		}
		
		
	}

}
