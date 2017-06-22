package com.ov.repositoryImpl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ov.model.GeneralModel;
import com.ov.repository.GeneralDao;

public abstract class GeneralDaoImpl<E extends GeneralModel> implements GeneralDao<E>{
	
	private Class<E> modelClass;
	protected MongoTemplate mongotpl;
	
	public GeneralDaoImpl(){
		
	}
	
	public MongoTemplate getMongotpl() {
		return mongotpl;
	}

	public void setMongotpl(MongoTemplate mongotpl) {
		this.mongotpl = mongotpl;
	}

	public Class<E> getModelClass() {
        if (modelClass == null) {
            ParameterizedType parent = (ParameterizedType) this.getClass().getGenericSuperclass();
            this.modelClass = (Class<E>) parent.getActualTypeArguments()[0];
        }
        return this.modelClass;
    }
	
	public E addCollection(E e) {
		if(!mongotpl.collectionExists(getModelClass())){
			mongotpl.createCollection(getModelClass());
		}
		mongotpl.insert(e);
		return e;
	}

	public E getCollection(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		E e = mongotpl.findOne(query, getModelClass());
		return e;
	}

	public List<E> getCollections() {
		return mongotpl.findAll(getModelClass());
	}

	public E updateCollection(E e) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(e.getId()));
		mongotpl.save(e);
		
		return e;
	}

	public E deleteCollection(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		E e= mongotpl.findOne(query, getModelClass());
		mongotpl.remove(e);
		return e;
	}

	public boolean checkExist(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		E e = mongotpl.findOne(query, getModelClass());
		if(e != null){
			return true;
		}
		return false;
	}


}
