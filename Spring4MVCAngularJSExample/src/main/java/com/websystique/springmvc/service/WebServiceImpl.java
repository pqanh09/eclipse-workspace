package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Web;
import com.websystique.springmvc.repositories.WebRepository;

@Service("webService")
public class WebServiceImpl implements WebService{
	
	@Autowired
	private WebRepository webRepository;
	
	public List<Web> findAllWebs() {
		return webRepository.findAll();
	}

	@Override
	public Web findByName(String name) {
		// TODO Auto-generated method stub
		return webRepository.findByName(name);
	}

	@Override
	public void saveWeb(Web web) {
		webRepository.safeSave(web);
	}

	@Override
	public void updateWeb(Web web) {
		webRepository.safeSave(web);
		
	}

	@Override
	public void deleteWeb(Web web) {
		webRepository.delete(web);
	}



}
