package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Manga;



public interface MangaService {
	
	Manga findByManganame(String Manganame);
	
	void saveManga(Manga Manga);
	
	void updateManga(Manga Manga);
	
	void deleteManga(Manga Manga);

	List<Manga> findAllMangas(); 
	
	void deleteAllMangas();
	
	public boolean isMangaExist(Manga Manga);
	
}
