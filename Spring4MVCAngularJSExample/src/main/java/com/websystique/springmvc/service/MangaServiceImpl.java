package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.repositories.ChapterRepository;
import com.websystique.springmvc.repositories.LinkRepository;
import com.websystique.springmvc.repositories.MangaRepository;
import com.websystique.springmvc.repositories.WebRepository;

@Service("userService")
public class MangaServiceImpl implements MangaService{
	
	
	@Autowired
	private MangaRepository mangaRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired 
	private WebRepository webRepository;
	
	@Autowired
	private LinkRepository linkRepository;

	@Override
	public Manga findByManganame(String Manganame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveManga(Manga Manga) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateManga(Manga Manga) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteManga(Manga Manga) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Manga> findAllMangas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllMangas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMangaExist(Manga Manga) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
