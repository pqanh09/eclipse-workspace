package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface BittrexService extends GenericService{
	GenericResponseObject getTotal(GenericRequestObject request);
	GenericResponseObject createJob(GenericRequestObject request);
	GenericResponseObject startJob(GenericRequestObject request);
	
}
