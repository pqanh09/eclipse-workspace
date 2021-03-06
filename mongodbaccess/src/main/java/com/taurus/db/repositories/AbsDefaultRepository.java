package com.taurus.db.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.taurus.db.model.GenericModel;

public abstract class AbsDefaultRepository <T extends GenericModel<ID>, ID extends Serializable> implements DefaultRepository<T, ID>{
	
//	@Autowired
//	@Qualifier(value="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	public List<T> searchByCriteria(Criteria criteria,Class<T> clazz, String... includedFields) {
		Query query = newQuery(criteria, includedFields);
		List<T> list = mongoTemplate.find(query, clazz);
		return list;
	}

	public void removeByCriteria(Criteria criteria,Class<T> clazz) {
		Query query = new Query();
		query.addCriteria(criteria);
		mongoTemplate.remove(query, clazz);
	}
	
	public void safeSave(T item) {
		mongoTemplate.save(item);
	}
	
	public void safeSave(Iterable<T> iterables) {
		mongoTemplate.save(iterables);
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
