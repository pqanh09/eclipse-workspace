package com.websystique.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.websystique.springmvc.configuration.MongodbConfiguration;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.repositories.UserRepository;
import com.websystique.springmvc.service.UserService;

public class MainClass {
	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongodbConfiguration.class);
		
		UserRepository userRepo = (UserRepository) context.getBean(UserRepository.class);
		userRepo.updateAddress("aaaaaaa");
		
		
		UserService userService = (UserService) context.getBean("userService");
		if(userService != null) {
			System.out.println("userService # null");
			userService.saveUser(new User("anhPham", "123", "pqanh@tma.com.vn"));
		} else {
			System.out.println("userService = null");
			
		}
	    System.out.println("OK");
	}
	
}
