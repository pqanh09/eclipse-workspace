package com.comics.springmvc.service;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.response.GenericResponseObject;

public interface MangaService extends GenericService{
	GenericResponseObject createMangaJob(GenericRequestObject request);
	GenericResponseObject updateMangaJob(GenericRequestObject request);
	
}
