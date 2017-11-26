package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.Chapter;

@NoRepositoryBean
public interface ChapterRepositoryCustom extends GenericRepositoryCustom<Chapter, ObjectId>{
	
}
