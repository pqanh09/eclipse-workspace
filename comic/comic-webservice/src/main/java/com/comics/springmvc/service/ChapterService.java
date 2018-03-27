package com.comics.springmvc.service;

import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.response.GenericResponseObject;

public interface ChapterService extends GenericService{
	//find by MangaId
	GenericResponseObject findByManga(GenericRequestObject request); 
	// find by ChapterId
	GenericResponseObject findOne(GenericRequestObject request); 
}
