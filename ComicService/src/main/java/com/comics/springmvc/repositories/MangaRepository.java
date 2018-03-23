package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.comics.springmvc.model.Manga;

@Repository
public interface MangaRepository extends GenericRepository<Manga, ObjectId>, MangaRepositoryCustom {
	Manga findByName(String name);
}
