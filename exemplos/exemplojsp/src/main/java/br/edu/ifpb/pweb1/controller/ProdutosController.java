package br.edu.ifpb.pweb1.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.model.dao.ProdutosDAO;
import br.edu.ifpb.pweb1.model.domain.Produto;

public class ProdutosController implements Command {

	private ProdutosDAO produtosDAO = null;
	
	public ProdutosController() {
		produtosDAO = new ProdutosDAO();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			Produto produto = null;
			String acao = request.getParameter("acao");
			String msgErro = null;
			switch(acao) {
				case "listar":
					request.setAttribute("produtos", produtosDAO.findAll());
					request.getRequestDispatcher("listarProdutos.jsp").forward(request, response);
					break;
				case "formulario":
					String id = request.getParameter("id");
					if (id != null) {
						produto = produtosDAO.findById(Long.parseLong(id)).orElseThrow( () -> new CommandException(404, "Produto não existe"));
						request.setAttribute("produto", produto);
					}
					request.setAttribute("operacao", id==null?"criar":"editar");
					request.getRequestDispatcher("formProdutos.jsp").forward(request, response);
					break;
				case "criar":
					if (request.getParameter("nome") == null || request.getParameter("nome").isEmpty()) {
						msgErro = "Nome obrigatório";
						request.setAttribute("msgErro", msgErro);
						request.setAttribute("operacao", "criar");
						request.getRequestDispatcher("formProdutos.jsp").forward(request, response);
					} else {
						produto = new Produto();
						produto.setNome(request.getParameter("nome"));
						produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
						produto.setDescricao(request.getParameter("descricao"));
						produto.setPreco(Double.parseDouble(request.getParameter("preco")));
						produto.setDataCadastro(Timestamp.from(Instant.now()));
						produtosDAO.insert(produto);
						response.sendRedirect("./api?comando=ProdutosController&acao=listar");
					}
					break;
				case "editar":
					produto = produtosDAO.findById(Long.parseLong(request.getParameter("id"))).orElseThrow(() -> new CommandException(404, "Produto não existe"));
					produto.setNome(request.getParameter("nome"));
					produto.setEstoque(Integer.parseInt(request.getParameter("estoque")));
					produto.setDescricao(request.getParameter("descricao"));
					produto.setPreco(Double.parseDouble(request.getParameter("preco")));
					produtosDAO.update(produto);
					response.sendRedirect("./api?comando=ProdutosController&acao=listar");
					break;
				case "excluir":
					produto = produtosDAO.findById(Long.parseLong(request.getParameter("id"))).orElseThrow(() -> new CommandException(404, "Produto não existe"));
					produtosDAO.delete(produto);
					response.sendRedirect("./api?comando=ProdutosController&acao=listar");
			}
			
		} catch (IOException | ServletException  e) {
			e.printStackTrace();
			throw new CommandException(500, e.getMessage());
		}
		
	}

}
