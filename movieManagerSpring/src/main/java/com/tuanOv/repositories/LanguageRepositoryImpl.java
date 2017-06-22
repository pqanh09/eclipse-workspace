package com.tuanOv.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tuanOv.models.Language;

public class LanguageRepositoryImpl implements CustomLanguageRepository {
	private MongoTemplate mongoTemplate;
	
	public LanguageRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public LanguageRepositoryImpl() {		
	}

	@Override
	public void deleteLanguageListByIdList(List<String> idList) throws Exception {
		Query query = new Query();
		
		if (idList != null && idList.size() > 0) {
			query.addCriteria(Criteria.where("_id").in(idList));
		}
		this.mongoTemplate.remove(query, Language.class);
	}
	
}
