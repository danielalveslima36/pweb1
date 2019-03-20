package br.edu.ifpb.pweb1.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.edu.ifpb.pweb1.model.dao.ProdutosDAO;
import br.edu.ifpb.pweb1.model.domain.Categoria;
import br.edu.ifpb.pweb1.model.domain.Produto;

@ManagedBean
@RequestScoped
public class ProdutosBean {

	private List<Produto> produtos;
	
	private List<Categoria> categorias;
	
	private List<SelectItem> categoriasItens;
	
	private Produto produto;
	
	private ProdutosDAO produtosDAO;
	
	public ProdutosBean() {}
	
	@PostConstruct
	public void init() {
		this.produtosDAO = new ProdutosDAO();
		this.produtos = produtosDAO.findAll();
		this.produto = new Produto();
		this.categorias = new ArrayList();
		this.categorias.add(new Categoria("tv", "Televisores"));
		this.categorias.add(new Categoria("pc", "Computadores"));
		this.categorias.add(new Categoria("cel", "Celulares"));
		this.categoriasItens = categorias.stream().map( cat -> new SelectItem(cat.getId(), cat.getNome()) ).collect(Collectors.toList());
	}
	
	public String abrirCadastro() {
		return "cadastro";
	}
	
	public String cadastrarProduto() {
		produto.setDataCadastro(Timestamp.from(Instant.now()));
		produtosDAO.save(produto);
		return "inicio";
	}
	
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
	
}
