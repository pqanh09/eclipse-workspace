package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Chapter;
import com.websystique.springmvc.model.User;

@Repository
public interface ChapterRepository extends GenericRepository<Chapter, ObjectId>, ChapterRepositoryCustom {
	User findByName(String name);
}
