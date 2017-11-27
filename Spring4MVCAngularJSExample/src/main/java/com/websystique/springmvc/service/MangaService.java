package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Manga;



public interface MangaService {
	
	Manga findByManganame(String name);
	
	void saveManga(Manga manga);
	
	void updateManga(Manga manga);
	
	void deleteManga(Manga manga);

	List<Manga> findAllMangas(); 
	
	
}
