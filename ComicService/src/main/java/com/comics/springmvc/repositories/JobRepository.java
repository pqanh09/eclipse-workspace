package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.comics.springmvc.model.Job;

@Repository
public interface JobRepository extends GenericRepository<Job, ObjectId>, JobRepositoryCustom {
	Job findByName(String name);
}
