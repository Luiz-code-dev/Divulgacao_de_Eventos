package util;

import javax.validation.constraints.NotBlank;

import org.primefaces.validate.bean.BeanValidationMetadataMapper;
import org.primefaces.validate.bean.NotBlankClientValidationConstraint;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
		
		BeanValidationMetadataMapper.registerConstraintMapping(NotBlank.class, 
				new NotBlankClientValidationConstraint());
	}

}