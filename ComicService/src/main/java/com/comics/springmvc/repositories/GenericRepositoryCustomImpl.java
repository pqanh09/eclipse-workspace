package com.comics.springmvc.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.comics.springmvc.model.GenericModel;

@NoRepositoryBean
public abstract class GenericRepositoryCustomImpl <T extends GenericModel<ID>, ID extends Serializable> implements GenericRepositoryCustom<T, ID>{
	
	@Autowired
//	@Qualifier(value="mongoTemplate")
	protected MongoTemplate mongoTemplate;
	
	public List<T> searchByCriteria(Criteria criteria, Class<T> clazz, String... includedFields) {
		Query query = newQuery(criteria, includedFields);
		List<T> list = mongoTemplate.find(query, clazz);
		return list;
	}

	public void removeByCriteria(Criteria criteria, Class<T> clazz) {
		Query query = new Query();
		query.addCriteria(criteria);
		mongoTemplate.remove(query, clazz);
	}
	
	public void safeSave(T item) {
		mongoTemplate.save(item);
	}
	
	public void safeSave(List<T> list) {
		for (T t : list) {
			mongoTemplate.save(t);
		}
	}
	
	 /**
     * Add included fields only, otherwise, get all fields
     * @param query
     * @param includedAttrs
     */
    private final void addIncludedFields(Query query, String... includedFields) {
      if (includedFields != null) {
        for (int index = 0; index < includedFields.length; index++) {
          query.fields().include(includedFields[index]);
        }
      }
    }
    
    /**
     * New query with included fields
     * @param criteria
     * @param includedFields
     * @return
     */
    private final Query newQuery(Criteria criteria, String...includedFields) {
      Query query = new Query(criteria);
      addIncludedFields(query, includedFields);
      return query;
    }
        
    
}
