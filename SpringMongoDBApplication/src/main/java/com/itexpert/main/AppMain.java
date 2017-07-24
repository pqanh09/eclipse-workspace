package com.itexpert.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.itexpert.app.config.AppConfig;
import com.itexpert.dao.impl.UserProfileRepository;

public class AppMain {

	public static void main(String args[]) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		UserProfileRepository userRepo = (UserProfileRepository) ctx.getBean(UserProfileRepository.class);
	}

}
