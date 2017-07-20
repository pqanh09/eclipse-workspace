package com.websystique.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.websystique.springmvc.configuration.MongodbConfiguration;
import com.websystique.springmvc.service.UserService;

public class MainClass {
	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongodbConfiguration.class);
	    //UserService userService = (UserService) context.getBean("userService");
	    
	    System.out.println("OK");
	}
	
}
