package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Link;
import com.websystique.springmvc.repositories.LinkRepository;

@Service("linkService")
public class LinkServiceImpl implements LinkService{
	
	@Autowired
	private LinkRepository linkRepository;
	
	public List<Link> findAllLinks() {
		return linkRepository.findAll();
	}

	@Override
	public List<Link> findByManga(String mangaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveLink(Link link) {
		linkRepository.safeSave(link);
	}

	@Override
	public void updateLink(Link link) {
		linkRepository.safeSave(link);
		
	}

	@Override
	public void deleteLink(Link link) {
		linkRepository.delete(link);
	}



}
