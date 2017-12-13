package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.UsdtTotal;

@NoRepositoryBean
public interface UsdtTotalRepositoryCustom extends GenericRepositoryCustom<UsdtTotal, ObjectId>{
	
}
