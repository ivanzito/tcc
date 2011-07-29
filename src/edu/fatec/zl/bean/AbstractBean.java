package edu.fatec.zl.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public abstract class AbstractBean {

	public static final String RESOURCE_BUNDLE = "edu.fatec.zl.i18n.bundle";
	
	public ResourceBundle getBundle() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return ResourceBundle.getBundle(RESOURCE_BUNDLE,locale);
	}	
}
