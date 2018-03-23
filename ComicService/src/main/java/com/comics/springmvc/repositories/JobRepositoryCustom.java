package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.Job;

@NoRepositoryBean
public interface JobRepositoryCustom extends GenericRepositoryCustom<Job, ObjectId>{
	
}
