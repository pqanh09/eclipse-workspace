package com.comics.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({ "com.websystique.springmvc.poller" })
@EnableMongoRepositories(basePackages="com.websystique.springmvc.repositories" )

public class TestConfiguration {
	// http://www.technicalkeeda.com/spring-tutorials/spring-4-mongodb-example
	
}
