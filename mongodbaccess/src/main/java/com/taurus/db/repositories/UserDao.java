package com.taurus.db.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {
	//@Autowired
	//MongoTemplate mongoTemplate;

	public void create() {
		System.out.println("Hello");
	}
}
