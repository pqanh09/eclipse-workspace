package com.comics.springmvc.service;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.response.GenericResponseObject;

public interface GenericService {
	GenericResponseObject create(GenericRequestObject request);
	
	GenericResponseObject update(GenericRequestObject request);
	
	GenericResponseObject delete(GenericRequestObject request);

	GenericResponseObject findAll(GenericRequestObject request); 
}
