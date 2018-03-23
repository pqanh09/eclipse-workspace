package com.comics.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.comics.springmvc.model.Web;

@Repository
public interface WebRepository extends GenericRepository<Web, ObjectId>, WebRepositoryCustom {
	Web findByName(String name);
}
