package com.ov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ov.model.GeneralModel;

//public interface GeneralRepository<E extends GeneralModel> extends MongoRepository<E, String>{
	public interface GeneralRepository<E extends GeneralModel> extends MongoRepository<E, String>{
	
}
