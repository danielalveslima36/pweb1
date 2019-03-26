package br.edu.ifpb.pweb1.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.pweb1.model.dao.UsuariosDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private UsuariosDAO usuariosDAO;
	
	private String login;
	
	private String senha;
	
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void init() {
		usuariosDAO = new UsuariosDAO();
	}
	
	public String efetuarLogin() throws Exception {
		
		Optional<Usuario> usuario = usuariosDAO.findByLogin(login);
		
		if (!usuario.isPresent() || !usuario.get().getSenha().equals(senha)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos", "Login ou senha inválidos"));
			return "";
		}
		
		usuarioLogado = usuario.map( u -> { u.setSenha(null); return u; }).get();
		
		login = null;
		senha = null;
		usuariosDAO = null;
		
		return "produtos";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}
