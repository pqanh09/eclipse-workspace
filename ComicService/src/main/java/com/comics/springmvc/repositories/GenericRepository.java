package com.comics.springmvc.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.GenericModel;

//No need implementation, just one interface, and you have CRUD, thanks Spring Data
@NoRepositoryBean
public interface GenericRepository <T extends GenericModel<ID>, ID extends Serializable> extends MongoRepository<T, ID>, GenericRepositoryCustom<T, ID> {

}
