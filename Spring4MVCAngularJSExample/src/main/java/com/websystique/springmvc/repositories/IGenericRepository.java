package com.websystique.springmvc.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.GenericModel;

//@NoRepositoryBean
public interface IGenericRepository <T extends GenericModel<ID>, ID extends Serializable> extends MongoRepository<T, ID>, IDefaultRepository<T, ID> {

}
