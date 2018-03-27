package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.Web;

@NoRepositoryBean
public interface WebRepositoryCustom extends GenericRepositoryCustom<Web, ObjectId>{
	
}
