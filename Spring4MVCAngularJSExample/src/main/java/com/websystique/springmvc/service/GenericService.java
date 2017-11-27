package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface GenericService {
	GenericResponseObject save(GenericRequestObject request);
	
	GenericResponseObject update(GenericRequestObject request);
	
	GenericResponseObject delete(GenericRequestObject request);

	GenericResponseObject findAll(GenericRequestObject request); 
}
