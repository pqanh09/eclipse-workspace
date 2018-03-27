package com.comics.springmvc.service;

import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.response.GenericResponseObject;

public interface JobService extends GenericService{
	GenericResponseObject pollManga(GenericRequestObject request);
	GenericResponseObject stopJob(GenericRequestObject request);
	GenericResponseObject startJob(GenericRequestObject request);
//	GenericResponseObject getJobInSchedule(GenericRequestObject request);
}
