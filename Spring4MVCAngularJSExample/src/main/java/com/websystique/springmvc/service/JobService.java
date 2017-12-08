package com.websystique.springmvc.service;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;

public interface JobService extends GenericService{
	GenericResponseObject pollManga(GenericRequestObject request);
	GenericResponseObject stopJob(GenericRequestObject request);
	GenericResponseObject startJob(GenericRequestObject request);
//	GenericResponseObject getJobInSchedule(GenericRequestObject request);
}
