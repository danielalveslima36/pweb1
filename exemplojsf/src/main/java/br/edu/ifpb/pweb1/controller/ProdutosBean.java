package br.edu.ifpb.pweb1.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.pweb1.model.dao.ProdutosDAO;
import br.edu.ifpb.pweb1.model.domain.Categoria;
import br.edu.ifpb.pweb1.model.domain.Produto;

@ManagedBean
@ViewScoped
public class ProdutosBean {

	private List<Produto> produtos;
	
	private List<Categoria> categorias;
	
	private List<SelectItem> categoriasItens;
	
	private Produto produto;
	
	private ProdutosDAO produtosDAO;
	
	private String estado;
	
//	private List<Produto> listaProdutos;
//	
//	private Produto produtoSelecionado;
	
	public ProdutosBean() {}
	
	@PostConstruct
	public void init() {
		this.produtosDAO = new ProdutosDAO();
		this.produto = new Produto();
		this.categorias = new ArrayList();
		this.categorias.add(new Categoria("tv", "Televisores"));
		this.categorias.add(new Categoria("pc", "Computadores"));
		this.categorias.add(new Categoria("cel", "Celulares"));
		this.categoriasItens = categorias.stream().map( cat -> new SelectItem(cat.getId(), cat.getNome()) ).collect(Collectors.toList());
//		this.listaProdutos = new ArrayList();
		listar();
	}
	
	public void listar() {
		this.estado = "listagem";
		this.produtos = produtosDAO.findAll();
	}
	
	public void abrirCadastro() {
		this.estado = "cadastro";
	}
	
	public String cadastrarProduto() {
		produto.setDataCadastro(Timestamp.from(Instant.now()));
		produtosDAO.save(produto);
		listar();
		return "inicio";
	}
	
	public void prepararEdicao() {
		this.estado = "edicao";
	}
	
	public void editarProduto() {
		produtosDAO.update(produto);
		listar();
	}
	
	public void removerProduto() {
		produtosDAO.delete(produto);
		listar();
	}
	
//	public void adicionarAoCarrinho() {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		listaProdutos = (List) request.getSession(true).getAttribute("carrinho");
//		if (listaProdutos == null) {
//			listaProdutos = new ArrayList();
//		}
//		listaProdutos.add(produtoSelecionado);
//		request.getSession(true).setAttribute("carrinho", listaProdutos);
//		produtoSelecionado = null;
//	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<SelectItem> getCategoriasItens() {
		return categoriasItens;
	}

	public void setCategoriasItens(List<SelectItem> categoriasItens) {
		this.categoriasItens = categoriasItens;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
//	public List<Produto> getListaProdutos() {
//		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		listaProdutos = (List) request.getSession(true).getAttribute("carrinho");
//		if (listaProdutos == null) {
//			listaProdutos = new ArrayList();
//		}
//		return listaProdutos;
//	}
//
//	public void setListaProdutos(List<Produto> listaProdutos) {
//		this.listaProdutos = listaProdutos;
//	}

//	public Produto getProdutoSelecionado() {
//		return produtoSelecionado;
//	}
//
//	public void setProdutoSelecionado(Produto produtoSelecionado) {
//		this.produtoSelecionado = produtoSelecionado;
//	}
	
	
	
}
