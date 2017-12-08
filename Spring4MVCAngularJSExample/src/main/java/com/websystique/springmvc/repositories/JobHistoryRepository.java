package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.JobHistory;

@Repository
public interface JobHistoryRepository extends GenericRepository<JobHistory, ObjectId>, JobHistoryRepositoryCustom {
	JobHistory findByJobId(String jobId);
}
