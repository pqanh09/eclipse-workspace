package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface JobService extends GenericService{
	GenericResponseObject pollManga(GenericRequestObject request);
}
