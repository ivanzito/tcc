package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class GenericBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String RESOURCE_BUNDLE = "edu.fatec.zl.i18n.bundle";
	
	public ResourceBundle getBundle() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return ResourceBundle.getBundle(RESOURCE_BUNDLE,locale);
	}	
	
	
    /**
     * Sets the current {@code Locale} for each user session
     *
     * @param languageCode - ISO-639 language code
     */
    public void changeLanguage(String language) {
    	Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

}
