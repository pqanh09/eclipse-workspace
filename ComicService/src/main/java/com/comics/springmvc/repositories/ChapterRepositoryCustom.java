package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.Chapter;

@NoRepositoryBean
public interface ChapterRepositoryCustom extends GenericRepositoryCustom<Chapter, ObjectId>{
	
}
