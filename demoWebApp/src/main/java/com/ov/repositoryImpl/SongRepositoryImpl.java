package com.ov.repositoryImpl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ov.model.Song;
import com.ov.repository.SongRepositoryCustom;


public class SongRepositoryImpl extends GeneralDaoImpl<Song> implements SongRepositoryCustom{
//	private MongoTemplate mongotpl;
//
//	public MongoTemplate getMongotpl() {
//		return mongotpl;
//	}
//
//	public void setMongotpl(MongoTemplate mongotpl) {
//		this.mongotpl = mongotpl;
//	}
	
}
