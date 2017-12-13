package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.UsdtHistory;

@NoRepositoryBean
public interface UsdtHistoryRepositoryCustom extends GenericRepositoryCustom<UsdtHistory, ObjectId>{
	
}
