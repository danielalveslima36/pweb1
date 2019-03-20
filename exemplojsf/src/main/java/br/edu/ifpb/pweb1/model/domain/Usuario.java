package br.edu.ifpb.pweb1.model.domain;

import java.sql.Timestamp;

import br.edu.ifpb.pweb1.model.dao.generic.Entity;
import br.edu.ifpb.pweb1.model.dao.generic.Table;

@Table(name="usuario")
public class Usuario extends Entity {
	
	private String login;
	
	private String nome;
	
	private String senha;
	
	private Timestamp dataCadastro;
	
	private boolean ativo;
	
	public Usuario() {
		super();
	}

	public Usuario(String login, String nome, String senha, Timestamp dataCadastro, boolean ativo) {
		super();
		this.login = login;
		this.nome = nome;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
