package edu.fatec.zl.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Classe utilizada para fazer leitura dos arquivos properties
 * @author E468735
 */
public class PropertiesUtil {

	private Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	private Properties property = null;
	
	/**
	 * 
	 */
	public PropertiesUtil(){
		super();
	}
	
	/**
	 * @param properties
	 */
	public PropertiesUtil(String properties){
		this.loadProperties(properties);
	}
	
	/**
	 * @param properties
	 * @return
	 */
	public Properties loadProperties(String properties){

		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream(properties);
		Properties prop = new Properties();
		try {
			prop.load(in);
			this.setProperty(prop);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		return prop;
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}
}
