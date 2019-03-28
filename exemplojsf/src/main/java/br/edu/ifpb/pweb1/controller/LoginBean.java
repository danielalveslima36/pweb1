package br.edu.ifpb.pweb1.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.pweb1.model.dao.UsuariosDAO;
import br.edu.ifpb.pweb1.model.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private UsuariosDAO usuariosDAO;
	
	private String nome;
	
	private String login;
	
	private String senha;
	
	private Usuario usuarioLogado;
	
//	private UIInput inputLogin;
	
	@PostConstruct
	public void init() {
		usuariosDAO = new UsuariosDAO();
	}
	
	public void verificarTipo(ActionEvent event) {
		String tipoForm = (String) event.getComponent().getAttributes().get("tipoForm");
		System.out.println("TIPO FORM = "+ tipoForm);
	}
	
	public String efetuarLogin() throws Exception {
		
//		boolean lembrar = Boolean.parseBoolean(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lembrar"));
		
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

//	public UIInput getInputLogin() {
//		return inputLogin;
//	}
//
//	public void setInputLogin(UIInput inputLogin) {
//		this.inputLogin = inputLogin;
//	}
	
	
	
	
}
