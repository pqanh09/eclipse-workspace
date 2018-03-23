package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.Manga;

@NoRepositoryBean
public interface MangaRepositoryCustom extends GenericRepositoryCustom<Manga, ObjectId>{
	
}
