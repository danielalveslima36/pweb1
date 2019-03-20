package br.edu.ifpb.pweb1.model.domain;

import java.sql.Timestamp;

import br.edu.ifpb.pweb1.model.dao.generic.Entity;
import br.edu.ifpb.pweb1.model.dao.generic.Table;

@Table(name="produto")
public class Produto extends Entity {
	
	private String nome;
	
	private Double preco;
	
	private String descricao;
	
	private Timestamp dataCadastro;
	
	private Integer estoque;
	
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
