package com.websystique.springmvc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

//@Repository("carDao")
public class CarDao {
	@Autowired
	MongoTemplate mongoTemplate;

	public void create() {
		System.out.println("Hello");
	}
}