package com.comics.springmvc.service;

import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.response.GenericResponseObject;

public interface MangaService extends GenericService{
	GenericResponseObject createMangaJob(GenericRequestObject request);
	GenericResponseObject updateMangaJob(GenericRequestObject request);
	
}
