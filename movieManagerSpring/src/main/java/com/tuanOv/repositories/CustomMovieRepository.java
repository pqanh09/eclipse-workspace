package com.tuanOv.repositories;

import java.util.List;

import com.tuanOv.models.Movie;

public interface CustomMovieRepository {
	public void deleteMovieListByIdList(List<String> idList) throws Exception;
	public List<Movie> getMovieListByIdList(List<String> idList) throws Exception;
}
