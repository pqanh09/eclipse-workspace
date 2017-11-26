package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.model.Web;

@Repository
public interface WebRepository extends GenericRepository<Web, ObjectId>, WebRepositoryCustom {
	User findByName(String name);
}
