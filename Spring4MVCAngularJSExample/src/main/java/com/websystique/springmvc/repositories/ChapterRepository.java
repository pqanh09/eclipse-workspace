package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Chapter;

@Repository
public interface ChapterRepository extends GenericRepository<Chapter, ObjectId>, ChapterRepositoryCustom {
	Chapter findByName(String name);
}
