package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UsdtInput;

@Repository
public interface UsdtInputRepository extends GenericRepository<UsdtInput, ObjectId>, UsdtInputRepositoryCustom {
}
