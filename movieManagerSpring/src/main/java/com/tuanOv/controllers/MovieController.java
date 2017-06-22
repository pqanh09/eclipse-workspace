package com.tuanOv.controllers;


import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tuanOv.models.Movie;
import com.tuanOv.models.MovieList;
import com.tuanOv.models.MovieQuery;
import com.tuanOv.models.RestResponseGeneral;
import com.tuanOv.models.ValidatedResult;
import com.tuanOv.services.MovieService;
import com.tuanOv.services.MovieValidatorService;
import com.tuanOv.services.UltilityService;

@Controller
@RequestMapping("/api/movies")
public class MovieController {
	private static final Logger logger = LoggerFactory.getLogger("com.tuanOv");
	
    @Autowired
    private ServletContext servletContext;
	
	private MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<RestResponseGeneral> getAllMovieList() {
		return this.movieService.getAllMovieList();
//		return UltilityService.getSuccessResponseEntityByData(new ArrayList<Movie>());
	}

	@RequestMapping(value="{id}", method =RequestMethod.GET)
	public @ResponseBody ResponseEntity<RestResponseGeneral> getMovieById(@PathVariable("id") String id) {
		return this.movieService.getMovieById(id);
	}

	
	@RequestMapping(method=RequestMethod.POST, consumes = {"multipart/form-data"})
	public @ResponseBody ResponseEntity<RestResponseGeneral> createMovie(@RequestPart("otherAttributes") Movie newMovie, @RequestPart("imageFile") MultipartFile imageFile) {
		try {
			String uploadedImageFilename = "";
			if (!imageFile.getOriginalFilename().equals("blob")) {
				String uploadPath = this.servletContext.getRealPath(MovieService.UPLOAD_PATH.RELATIVE_PATH.getValue());
				uploadedImageFilename = this.movieService.saveAndGetUploadedFilename(imageFile, uploadPath);				
			}			
			
			newMovie.setImageFilename(uploadedImageFilename);
			
			ValidatedResult validRes = MovieValidatorService.validateAdd(newMovie);
			
			if (validRes.getResult()) {
				return this.movieService.createMovie(newMovie);
			}
			
			logger.error("COULD NOT CREATE MOVIE!!! Validate Message is " + validRes.getMessage());
			logger.error("COULD NOT CREATE MOVIE!!! Validate Message is {0}",  validRes.getMessage());
			
			return UltilityService.getErrorResponseEntityByMessage(validRes.getMessage());
			
		} catch (Exception e) {
			return UltilityService.getErrorResponseEntityByMessage(e.getMessage());
		}	
		
				
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT, consumes = {"multipart/form-data"})
	public @ResponseBody ResponseEntity<RestResponseGeneral> editMovie(@RequestPart("otherAttributes") Movie editedMovie, @PathVariable("id") String id, @RequestPart("imageFile") MultipartFile imageFile) {
		try {
			String uploadedImageFilename = "";
			
			if (!imageFile.getOriginalFilename().equals("blob")) {
				String uploadPath = this.servletContext.getRealPath(MovieService.UPLOAD_PATH.RELATIVE_PATH.getValue());				
				uploadedImageFilename = this.movieService.saveAndGetUploadedFilename(imageFile, uploadPath);
			}
			
			editedMovie.setImageFilename(uploadedImageFilename);
			
			ValidatedResult validRes = MovieValidatorService.validateEdit(editedMovie);
			
			if (validRes.getResult()) {
				return this.movieService.editMovie(editedMovie, id);
			}
			
			return UltilityService.getErrorResponseEntityByMessage(validRes.getMessage());
			
		} catch (Exception e) {
			return UltilityService.getErrorResponseEntityByMessage(e.getMessage());
		}
		
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<RestResponseGeneral> deleteMovieById(@PathVariable("id") String id) {
		return this.movieService.deleteMovieById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<RestResponseGeneral> deleteMovieList(@RequestBody MovieList deletedMovieList) {
		return this.movieService.deleteMovieList(deletedMovieList);	
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<RestResponseGeneral> findMovieListByTitleOrDescription(@RequestBody MovieQuery movieQuery) {
		return this.movieService.findMovieListByTitleOrDescription(movieQuery);	
	}
	
}
