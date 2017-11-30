package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface ChapterService extends GenericService{
	//find by MangaId
	GenericResponseObject findByManga(GenericRequestObject request); 
	// find by ChapterId
	GenericResponseObject findOne(GenericRequestObject request); 
}
