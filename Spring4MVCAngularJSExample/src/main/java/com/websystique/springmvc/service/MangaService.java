package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface MangaService extends GenericService{
	GenericResponseObject createMangaJob(GenericRequestObject request);
	GenericResponseObject updateMangaJob(GenericRequestObject request);
	
}
