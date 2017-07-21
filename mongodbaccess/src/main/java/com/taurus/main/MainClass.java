package com.taurus.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.taurus.configuration.MongodbConfiguration;
import com.taurus.db.repositories.UserDao;

public class MainClass {
	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongodbConfiguration.class);
		UserDao userService = (UserDao) context.getBean("userDao");
	    
	    System.out.println("OK");
	}
	
}
