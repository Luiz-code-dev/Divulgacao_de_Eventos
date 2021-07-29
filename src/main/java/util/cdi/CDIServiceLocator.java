package util.cdi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.faces.mgbean.BeanManager;

public class CDIServiceLocator {
	
	@SuppressWarnings("unused")
	private static BeanManager getBeanManager() {
		try {
			InitialContext initialContext = new InitialContext();
			return (BeanManager) initialContext.lookup("java:comp/env/BeanManager");
		} catch (NamingException e) {
			throw new RuntimeException("Não pôde encontrar BeanManager no JNDI.");
		}
	}


}
