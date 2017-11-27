package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Web;



public interface WebService {
	
	
	Web findByName(String name);
	
	void saveWeb(Web web);
	
	void updateWeb(Web web);
	
	void deleteWeb(Web web);

	List<Web> findAllWebs(); 
	
	
}
