package com.tuanOv.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuanOv.models.Language;

public interface LanguageRepository extends MongoRepository<Language, String>, CustomLanguageRepository {	
	
	List<Language> findByNameLikeIgnoreCaseOrAliasLikeIgnoreCase(String name, String alias);
}
