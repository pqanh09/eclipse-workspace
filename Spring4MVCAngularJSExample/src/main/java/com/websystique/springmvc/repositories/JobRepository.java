package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Job;

@Repository
public interface JobRepository extends GenericRepository<Job, ObjectId>, JobRepositoryCustom {
	Job findByName(String name);
}
