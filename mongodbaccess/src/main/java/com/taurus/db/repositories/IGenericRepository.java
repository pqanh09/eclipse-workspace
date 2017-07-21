package com.taurus.db.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.taurus.db.model.GenericModel;

//@NoRepositoryBean
public interface IGenericRepository <T extends GenericModel<ID>, ID extends Serializable> extends MongoRepository<T, ID>, IDefaultRepository<T, ID> {

}
