package com.tuanOv;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@ImportResource({"classpath:applicationContext.xml"})
public class AppConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {    	
    	
    	String location = "";
    	long maxFileSize = 5242880;
    	long maxRequestSize = 20971520;
    	int fileSizeThreshold = 0;

        MultipartConfigElement multipartConfigElement = 
            new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);

        registration.setMultipartConfig(multipartConfigElement);

    }
    

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
}