package com.comics.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.request.JobRequestObject;
import com.comics.springmvc.request.ObjectType;
import com.comics.springmvc.request.RequestType;
import com.comics.springmvc.response.GenericResponseObject;
import com.comics.springmvc.service.JobService;

@RestController
@RequestMapping("/api")
public class JobController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	JobService jobService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Job--------------------------------------------------------

	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> listAllUsers() {
		GenericResponseObject responseObject = jobService.findAll(new GenericRequestObject(RequestType.read, ObjectType.Job, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}


	// ------------------- Delete Job(s)
	// --------------------------------------------------------

	@RequestMapping(value = "/job", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseObject> deleteUser(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.delete);
		GenericResponseObject responseObject = jobService.delete(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	// ------------------- poll Job(s)
	// --------------------------------------------------------
	@RequestMapping(value = "/job/pollmanga", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> pollManga(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.poll);
		GenericResponseObject responseObject = jobService.pollManga(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	// ------------------- stop  Job(s)
	// --------------------------------------------------------
	@RequestMapping(value = "/job/stop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> cancelJob(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.stop);
		GenericResponseObject responseObject = jobService.stopJob(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	// ------------------- stop  Job(s)
	// --------------------------------------------------------
	@RequestMapping(value = "/job/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> startJob(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.start);
		GenericResponseObject responseObject = jobService.startJob(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
//	// ------------------- get  Job(s) are running
//	// --------------------------------------------------------
//	@RequestMapping(value = "/job/inschedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<GenericResponseObject> getJobInSchedule() {
//		GenericResponseObject responseObject = jobService.getJobInSchedule(new GenericRequestObject(RequestType.read, ObjectType.Job, null));
//		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
//	}


}