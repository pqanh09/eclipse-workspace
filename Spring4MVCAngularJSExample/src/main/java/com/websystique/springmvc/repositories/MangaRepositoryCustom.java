package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.Manga;

@NoRepositoryBean
public interface MangaRepositoryCustom extends GenericRepositoryCustom<Manga, ObjectId>{
	
}
