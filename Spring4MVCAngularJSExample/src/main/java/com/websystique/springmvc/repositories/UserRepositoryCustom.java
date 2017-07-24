package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.User;

@NoRepositoryBean
public interface UserRepositoryCustom extends GenericRepositoryCustom<User, ObjectId>{
	//
	int updateAddress(String adress);
}
