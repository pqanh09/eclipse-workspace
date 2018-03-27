package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.comics.springmvc.model.Chapter;

@Repository
public interface ChapterRepository extends GenericRepository<Chapter, ObjectId>, ChapterRepositoryCustom {
	Chapter findByName(String name);
}
