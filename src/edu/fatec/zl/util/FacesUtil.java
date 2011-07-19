package edu.fatec.zl.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FacesUtil implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @return
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    
    /**
     * @param name
     * @return
     */
    public String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * @return
     */
    public String getBundleName() {
        return getFacesContext().getApplication().getMessageBundle();
    }

    /**
     * @return
     */
    public ResourceBundle getBundle() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return ResourceBundle.getBundle(getBundleName(), getRequest().getLocale(), classLoader);
    }

    /**
     * @param key
     * @return
     */
    public String getText(String key) {
        String message;

        try {
            message = getBundle().getString(key);
        } catch (java.util.MissingResourceException mre) {
            return "???" + key + "???";
        }

        return message;
    }

    /**
     * @param key
     * @param arg
     * @return
     */
    public String getText(String key, Object arg) {
        if (arg == null) {
            return getText(key);
        }

        MessageFormat form = new MessageFormat(getBundle().getString(key));

        if (arg instanceof String) {
            return form.format(new Object[]{arg});
        } else if (arg instanceof Object[]) {
            return form.format(arg);
        } else {
            return "";
        }
    }

    /**
     * @param key
     * @param arg
     */
    @SuppressWarnings("unchecked")
    protected void addMessage(String key, Object arg) {
        @SuppressWarnings("rawtypes")
		List<String> messages = (List) getSession().getAttribute("messages");

        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(getText(key, arg));
        getSession().setAttribute("messages", messages);
    }

    /**
     * @param key
     */
    protected void addMessage(String key) {
        addMessage(key, null);
    }

    /**
     * @param key
     * @param arg
     */
    @SuppressWarnings("unchecked")
    protected void addError(String key, Object arg) {
        @SuppressWarnings("rawtypes")
		List<String> errors = (List) getSession().getAttribute("errors");

        if (errors == null) {
            errors = new ArrayList<String>();
        }

        // if key contains a space, don't look it up, it's likely a raw message
        if (key.contains(" ") && arg == null) {
            errors.add(key);
        } else {
            errors.add(getText(key, arg));
        }

        getSession().setAttribute("errors", errors);
    }

    protected void addError(String key) {
        addError(key, null);
    }
    
    
    /**
     * Servlet API Convenience method
     *
     * @return HttpServletRequest from the FacesContext
     */
    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }


    public HttpServletResponse getResponse() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }
}
