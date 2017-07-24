package com.taurus.db.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.taurus.db.model.GenericModel;

@NoRepositoryBean
public interface GenericRepository <T extends GenericModel<ID>, ID extends Serializable> extends MongoRepository<T, ID>{

}
