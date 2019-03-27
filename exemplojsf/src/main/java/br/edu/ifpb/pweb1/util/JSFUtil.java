package br.edu.ifpb.pweb1.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.pweb1.controller.LoginBean;

public class JSFUtil {
	
	public HttpSession getSession() {
		return ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
	}
	
	public LoginBean getLoginBean() {
		LoginBean loginBean = (LoginBean)((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("loginBean");
		return loginBean;
	}

}
