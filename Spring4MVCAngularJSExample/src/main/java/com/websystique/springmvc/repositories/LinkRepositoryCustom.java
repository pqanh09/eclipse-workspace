package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.Link;

@NoRepositoryBean
public interface LinkRepositoryCustom extends GenericRepositoryCustom<Link, ObjectId>{
	
}
