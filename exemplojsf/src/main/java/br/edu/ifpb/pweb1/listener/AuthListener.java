package br.edu.ifpb.pweb1.listener;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb1.controller.LoginBean;

public class AuthListener implements PhaseListener {
	
	@Override
	public void afterPhase(PhaseEvent event) {
		String viewId = event.getFacesContext().getViewRoot().getViewId();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		if (request.getSession(false) == null) {
			event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context, null, "goLogin");
		}

		LoginBean loginBean = (LoginBean)request.getSession(false).getAttribute("loginBean");
		if (!viewId.equals("/loja/login.xhtml")) {
			if (loginBean == null || loginBean.getUsuarioLogado() == null) {
				event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context, null, "goLogin");
			}
		} else {
			if (loginBean != null && loginBean.getUsuarioLogado() != null) {
				event.getFacesContext().getApplication().getNavigationHandler().handleNavigation(context, null, "produtos");
			}
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
