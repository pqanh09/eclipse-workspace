package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UsdtHistory;

@Repository
public interface UsdtHistoryRepository extends GenericRepository<UsdtHistory, ObjectId>, UsdtHistoryRepositoryCustom {
	UsdtHistory findByTime(long name);
}
