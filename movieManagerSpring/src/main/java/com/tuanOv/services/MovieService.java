package com.tuanOv.services;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.tuanOv.models.MFile;
import com.tuanOv.models.Movie;
import com.tuanOv.models.MovieList;
import com.tuanOv.models.MovieQuery;
import com.tuanOv.models.RestResponseGeneral;
import com.tuanOv.repositories.MovieRepository;

@Service
public class MovieService {
	public static String UPLOAD_LOCATION = "D://temp_upload_file//"; 
	
	
	private ServletContext servletContext;
	
	private MovieRepository movieRepository;
	
	public enum UPLOAD_PATH {
		RELATIVE_PATH("/WEB-INF/temp_upload_file/"),
		MAPPED_REALTIVE_PATH("/movie/imageFile/");
		
		private String value;
		
		UPLOAD_PATH(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}	
	
	
	public MovieService(MovieRepository movieRepository, ServletContext servletContext) {
		this.movieRepository = movieRepository;
		this.servletContext = servletContext;
	}
	
	public ResponseEntity<RestResponseGeneral> getAllMovieList() {
		String message;
		try {
			List<Movie> result = movieRepository.findAll();
			if (result.size() > 0) {
				return UltilityService.getSuccessResponseEntityByData(result);
			} else {
				message = "Data is empty!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}	
	
	public String saveAndGetUploadedFilename(MultipartFile file, String uploadPath) throws IOException {		
		byte[] bytes = file.getBytes();
		
		MFile tempFile = MFile.getMFileByFullFilename(file.getOriginalFilename());
		String filename = tempFile.getFilename() + "__" + new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss_SSS").format(new Date()) + "." + tempFile.getExtension();
        Path path = Paths.get(uploadPath + filename);
        Files.write(path, bytes);
        
        return filename;
	}	
	
	public ResponseEntity<RestResponseGeneral> getMovieById(String id) {
		String message;
		try {
			Movie movie = movieRepository.findOne(id);
			if (movie == null) {
				message = "No data found!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				return UltilityService.getSuccessResponseEntityByData(movie);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> createMovie(Movie newMovie) {
		String message;
		try {
			newMovie.autoSetCreatedAt();
			newMovie.autoSetUpdatedAt();			
			movieRepository.save(newMovie);
			message = "Created new movie successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);			
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public boolean deleteFileByFilename(String filename) throws Exception {
		String filePath = this.servletContext.getRealPath(MovieService.UPLOAD_PATH.RELATIVE_PATH.getValue()) + filename;
		return deleteFileByAbsolutePath(filePath);
	}
	
	public boolean deleteFileByAbsolutePath(String filePath) throws Exception {
		File file = new File(filePath);
		
		if (file.delete()) {
			return true;
		}
		
		return false;
	}
	
	public ResponseEntity<RestResponseGeneral> editMovie(Movie editedMovie, @PathVariable("id") String id) {
		String message = "";
		
		try {
			Movie movieToEdit = movieRepository.findOne(id);
			if (movieToEdit == null) {
				message = "Not found such movie to edit!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				movieToEdit.setTitle(editedMovie.getTitle());
				movieToEdit.setDescription(editedMovie.getDescription());
				movieToEdit.setPrice(editedMovie.getPrice());
				movieToEdit.setLanguage(editedMovie.getLanguage());
				if (!editedMovie.getImageFilename().isEmpty()) {
					deleteFileByFilename(movieToEdit.getImageFilename());
					movieToEdit.setImageFilename(editedMovie.getImageFilename());
				}
								
				movieToEdit.autoSetUpdatedAt();
				Movie returnedMovie = movieRepository.save(movieToEdit);
				message = "Edited movie successfully!";
				return UltilityService.getSuccessResponseEntityByDataAndMessage(returnedMovie, message);
			}
		} catch (Exception e) {
			message = e.getMessage();
			try {
				deleteFileByFilename(editedMovie.getImageFilename());
			} catch (Exception e1) {
				message += System.lineSeparator() + e1.getMessage();
			}
			
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> deleteMovieById(String id) {
		String message;
		try {
			movieRepository.delete(id);
			message = "Delete selected item successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}	
	
	public ResponseEntity<RestResponseGeneral> deleteMovieList(MovieList movieList) {
		String message;
		try {
			List<String> idList = movieList.getMovieList();
			List<Movie> deletedMovieList = movieRepository.getMovieListByIdList(idList);
			for (Movie movie : deletedMovieList) {
				deleteFileByFilename(movie.getImageFilename());
				movieRepository.delete(movie);
			}
//			movieRepository.deleteMovieListByIdList(idList);
			message = "Delete selected item(s) successfully!";
			return UltilityService.getSuccessResponseEntityByMessage(message);
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
	public ResponseEntity<RestResponseGeneral> findMovieListByTitleOrDescription(MovieQuery movieQuery) {
		String message;
		try {
			List<Movie> movieList = movieRepository.findByTitleLikeIgnoreCaseOrDescriptionLikeIgnoreCase(movieQuery.getTitle(), movieQuery.getDescription());
			if (movieList.size() == 0) {
				message = "No data found!";
				return UltilityService.getErrorResponseEntityByMessage(message);
			} else {
				return UltilityService.getSuccessResponseEntityByData(movieList);
			}
		} catch (Exception e) {
			message = e.getMessage();
			return UltilityService.getErrorResponseEntityByMessage(message);
		}
	}
	
}
