package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.model.User;

@Repository
public interface MangaRepository extends GenericRepository<Manga, ObjectId>, MangaRepositoryCustom {
	User findByName(String name);
}
