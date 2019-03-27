package br.edu.ifpb.pweb1.controller;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.model.dao.UsuariosDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private UsuariosDAO usuariosDAO;
	
	private String login;
	
	private String senha;
	
	private Usuario usuarioLogado;
	
//	private UIInput inputLogin;
	
	@PostConstruct
	public void init() {
		usuariosDAO = new UsuariosDAO();
	}
	
	public String efetuarLogin() throws Exception {
		
		Optional<Usuario> usuario = usuariosDAO.findByLogin(login);
		
//		if (login.length() > 10) {
//			FacesContext.getCurrentInstance().addMessage(inputLogin.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login excede o número de caracters permitido", "Login excede o número de caracters permitido"));
//			return "";
//		}
		
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
	
	public String logout() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.getSession(false).invalidate();
		return "goLogin";
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

//	public UIInput getInputLogin() {
//		return inputLogin;
//	}
//
//	public void setInputLogin(UIInput inputLogin) {
//		this.inputLogin = inputLogin;
//	}
	
	
	
}
