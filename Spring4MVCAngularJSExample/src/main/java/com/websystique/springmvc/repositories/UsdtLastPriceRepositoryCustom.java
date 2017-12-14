package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.UsdtLastPrice;

@NoRepositoryBean
public interface UsdtLastPriceRepositoryCustom extends GenericRepositoryCustom<UsdtLastPrice, ObjectId>{
	
}
