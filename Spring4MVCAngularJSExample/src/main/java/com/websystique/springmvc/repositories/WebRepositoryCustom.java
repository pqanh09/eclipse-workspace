package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.Web;

@NoRepositoryBean
public interface WebRepositoryCustom extends GenericRepositoryCustom<Web, ObjectId>{
	
}
