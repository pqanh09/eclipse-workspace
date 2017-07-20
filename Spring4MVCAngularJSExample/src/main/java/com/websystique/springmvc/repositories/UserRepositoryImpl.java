package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;

//@Repository
//@Qualifier("userRepository")
public class UserRepositoryImpl extends AbsDefaultRepository<User, ObjectId> implements UserRepositoryCustom{

	@Autowired
	@Qualifier(value="mongoTemplate")
	protected MongoTemplate mongoTemplate;
	@Override
	public int updateAddress(String adress) {
		// TODO Auto-generated method stub
		return 0;
	}

}
