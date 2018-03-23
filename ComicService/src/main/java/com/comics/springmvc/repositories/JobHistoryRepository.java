package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.comics.springmvc.model.JobHistory;

@Repository
public interface JobHistoryRepository extends GenericRepository<JobHistory, ObjectId>, JobHistoryRepositoryCustom {
	JobHistory findByJobId(String jobId);
}
