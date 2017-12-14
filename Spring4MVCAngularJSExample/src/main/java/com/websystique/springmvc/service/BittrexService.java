package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface BittrexService extends GenericService{
	GenericResponseObject getInput(GenericRequestObject request);
	GenericResponseObject updateInput(GenericRequestObject request);
	GenericResponseObject getTotal(GenericRequestObject request);
	GenericResponseObject createJob(GenericRequestObject request);
	
}
