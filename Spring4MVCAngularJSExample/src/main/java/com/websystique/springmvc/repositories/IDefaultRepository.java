package com.websystique.springmvc.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.NoRepositoryBean;

import com.websystique.springmvc.model.GenericModel;
//@NoRepositoryBean
public interface IDefaultRepository<T extends GenericModel<ID>, ID extends Serializable> {
	public List<T> searchByCriteria(Criteria criteria,Class<T> clazz, String... includedFields);

	public void removeByCriteria(Criteria criteria,Class<T> clazz);
	
	public void safeSave(T item);
	
	public void safeSave(Iterable<T> iterables);
}
