package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.JobHistory;

@NoRepositoryBean
public interface JobHistoryRepositoryCustom extends GenericRepositoryCustom<JobHistory, ObjectId>{
	
}
