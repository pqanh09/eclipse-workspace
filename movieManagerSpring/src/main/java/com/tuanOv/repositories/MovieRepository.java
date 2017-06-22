package com.tuanOv.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tuanOv.models.Movie;

public interface MovieRepository extends MongoRepository<Movie, String>, CustomMovieRepository {
	List<Movie> findByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCase(String title, String description);
}
