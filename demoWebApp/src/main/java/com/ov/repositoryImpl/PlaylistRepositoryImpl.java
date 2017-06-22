package com.ov.repositoryImpl;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ov.model.Playlist;
import com.ov.repository.PlaylistRepositoryCustom;

public class PlaylistRepositoryImpl extends GeneralDaoImpl<Playlist> implements PlaylistRepositoryCustom{
//	private MongoTemplate mongotpl;
//	public PlaylistRepositoryImpl() {
//	}
//	public MongoTemplate getMongotpl() {
//		return mongotpl;
//	}
//	public void setMongotpl(MongoTemplate mongotpl) {
//		this.mongotpl = mongotpl;
//	}
	
//	public List<Playlist> getBySongid(String id){
//		Aggregation agg = newAggregation(
//				match(Criteria.where("songIds").in(id))
////				group("hosting").count().as("total"),
////				project("total").and("hosting").previousOperation(),
////				sort(Sort.Direction.DESC, "total")
//
//			);
////		Query query = new Query();
////		query.addCriteria(Criteria.where("songIds").in(idSong));
//		AggregationResults<Playlist> groupResults = mongotpl.aggregate(agg, Playlist.class, Playlist.class);
//		List<Playlist> playlists = groupResults.getMappedResults();
//		return playlists;
//	}
	
	
}
