package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Link;

@Repository
public interface LinkRepository extends GenericRepository<Link, ObjectId>, LinkRepositoryCustom {
}
