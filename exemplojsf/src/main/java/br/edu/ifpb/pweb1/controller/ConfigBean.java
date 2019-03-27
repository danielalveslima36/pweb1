package br.edu.ifpb.pweb1.controller;

import java.util.Locale;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@ApplicationScoped
public class ConfigBean {
	
	private String nomeLoja = "Loja PWEB1";

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}
	
}
