package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.JobHistory;

@NoRepositoryBean
public interface JobHistoryRepositoryCustom extends GenericRepositoryCustom<JobHistory, ObjectId>{
	
}
