package com.taurus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan({ "com.taurus" })
@PropertySource(value = { "classpath:application.properties" })
@EnableMongoRepositories(basePackages="com.taurus.db.repositories" )
public class MongodbConfiguration {
	// http://www.technicalkeeda.com/spring-tutorials/spring-4-mongodb-example

	@Autowired
	private Environment environment;

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		MongoClient mongoClient = new MongoClient(environment.getRequiredProperty("mongo.host"),
				Integer.valueOf(environment.getRequiredProperty("mongo.port")));
		UserCredentials userCredentials = new UserCredentials("", "");
		return new SimpleMongoDbFactory(mongoClient, environment.getRequiredProperty("mongo.dbname"), userCredentials);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
}
