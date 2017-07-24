package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;

public class UserRepositoryImpl extends GenericRepositoryCustomImpl<User, ObjectId> implements UserRepositoryCustom{

	
	@Override
	public int updateAddress(String adress) {
		// TODO Auto-generated method stub
		if(mongoTemplate != null){
			System.out.println("mongoTemplate: # null");
		}
		else {
			System.out.println("mongoTemplate: = null");
		}
		return 0;
	}

}
