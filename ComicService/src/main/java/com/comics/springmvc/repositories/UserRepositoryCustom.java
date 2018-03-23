package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.User;

@NoRepositoryBean
public interface UserRepositoryCustom extends GenericRepositoryCustom<User, ObjectId>{
	//
	int updateAddress(String adress);
}
