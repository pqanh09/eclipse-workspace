package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.UsdtInput;

@NoRepositoryBean
public interface UsdtInputRepositoryCustom extends GenericRepositoryCustom<UsdtInput, ObjectId>{
	
}
