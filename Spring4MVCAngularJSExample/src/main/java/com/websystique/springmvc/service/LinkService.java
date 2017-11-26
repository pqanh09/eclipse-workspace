package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Link;



public interface LinkService {
	
	
	List<Link> findByManga(String mangaId);
	
	void saveLink(Link link);
	
	void updateLink(Link link);
	
	void deleteLink(Link link);

	List<Link> findAllLinks(); 
	
	
}
