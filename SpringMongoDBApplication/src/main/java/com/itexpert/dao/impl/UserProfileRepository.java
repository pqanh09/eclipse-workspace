package com.itexpert.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.itexpert.dao.BaseUserDAO;
import com.itexpert.mongo.model.UserProfile;

@Component("userProfileRepository")
public class UserProfileRepository implements BaseUserDAO<UserProfile>{

	@Autowired
	private MongoTemplate mongoTemplate;

	public void create(UserProfile user) {
		mongoTemplate.save(user);
	}


	public void remove(UserProfile id) {
		// TODO Auto-generated method stub
		
	}

	public void removeByEmail(String id) {
		// TODO Auto-generated method stub
		
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

	public UserProfile find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List findAll() {
		return mongoTemplate.findAll(UserProfile.class);
	}

	public void update(UserProfile user) {
		// TODO Auto-generated method stub
		
	}

	public UserProfile getUserProfileByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
