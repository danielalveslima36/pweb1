package br.edu.ifpb.pweb1.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="AuthFilter", urlPatterns = "/inicio.jsp")
public class AuthFilter implements Filter {

	private static Logger log = Logger.getLogger("AuthFilter");
	
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Authfilter init");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String usuarioLogado = (String) ((HttpServletRequest)request).getSession(true).getAttribute("usuarioLogado");
		if (usuarioLogado == null) {
			((HttpServletRequest)request).getSession(true).setAttribute("msgerro", "Você não pode acessar essa página. Área restrita. Autentique-se primeiro.");
//			request.setAttribute("msgerro", "Você não pode acessar essa página. Área restrita. Autentique-se primeiro.");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
			((HttpServletResponse)response).sendRedirect("login.jsp");
		}
		chain.doFilter(request, response);
		log.info("APOS PROCESSAR A REQUISICAO");
	}

	public void destroy() {
		log.info("Authfilter destroy");
	}

}
