package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UsdtTotal;

@Repository
public interface UsdtTotalRepository extends GenericRepository<UsdtTotal, ObjectId>, UsdtTotalRepositoryCustom {
	UsdtTotal findByName(String name);
}
