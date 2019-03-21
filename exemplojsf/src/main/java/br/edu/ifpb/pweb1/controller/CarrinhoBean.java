package br.edu.ifpb.pweb1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.pweb1.model.domain.Produto;

@ManagedBean
@SessionScoped
public class CarrinhoBean {
	
	private List<Produto> produtos;
	
	private Produto produtoSelecionado;
	
	@PostConstruct
	public void init() {
		this.produtos = new ArrayList();
		//Carregando bean de aplicação:
		ServletContext context = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
		ConfigBean configBean = (ConfigBean)context.getAttribute("configBean");
		
		//Carregando bean de sessao:
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		CarrinhoBean carrinhoBean = (CarrinhoBean)request.getSession().getAttribute("carrinhoBean");
		
	}
	
	public void adicionarProduto() {
		produtos.add(produtoSelecionado);
	}
	
	public void removerProduto() {
		produtos.remove(produtoSelecionado);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	
	
}
