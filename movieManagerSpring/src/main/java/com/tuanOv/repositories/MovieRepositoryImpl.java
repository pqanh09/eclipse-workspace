package com.tuanOv.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tuanOv.models.Movie;

public class MovieRepositoryImpl implements CustomMovieRepository {	
	private MongoTemplate mongoTemplate;
	
	public MovieRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void deleteMovieListByIdList(List<String> idList) throws Exception {
		Query query = new Query();
		
		if (idList != null && idList.size() > 0) {
			query.addCriteria(Criteria.where("_id").in(idList));
		}
		this.mongoTemplate.remove(query, Movie.class);
	}
	
	@Override
	public List<Movie> getMovieListByIdList(List<String> idList) throws Exception {
		List<Movie> result = null;
		Query query = new Query();
		
		if (idList != null && idList.size() > 0) {
			query.addCriteria(Criteria.where("_id").in(idList));
		}
		result = this.mongoTemplate.find(query, Movie.class);
		
		return result;
	}
	
	
	
}
