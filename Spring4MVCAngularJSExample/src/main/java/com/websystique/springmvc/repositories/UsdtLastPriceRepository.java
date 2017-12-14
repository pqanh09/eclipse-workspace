package com.websystique.springmvc.repositories;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.UsdtLastPrice;

@Repository
public interface UsdtLastPriceRepository extends GenericRepository<UsdtLastPrice, ObjectId>, UsdtLastPriceRepositoryCustom {
	UsdtLastPrice findByTime(long name);
}
