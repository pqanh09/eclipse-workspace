package com.taurus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.taurus.configuration.MongodbConfiguration;
import com.taurus.db.repositories.UserRepository;

public class MainClass {
	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongodbConfiguration.class);
		UserRepository userRepository = (UserRepository) context.getBean("UserRepository");
	    
	    System.out.println("OK");
	}
	
}
