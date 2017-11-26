package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.User;

@Repository
public interface JobRepository extends GenericRepository<Job, ObjectId>, JobRepositoryCustom {
	User findByName(String name);
}
