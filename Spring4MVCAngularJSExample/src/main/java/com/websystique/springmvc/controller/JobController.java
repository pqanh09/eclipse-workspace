package com.websystique.springmvc.controller;

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

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.JobRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.service.JobService;

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


	// -------------------Create a Job

	@RequestMapping(value = "/job", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> createUser(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = jobService.create(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Update a Job
	// --------------------------------------------------------

	@RequestMapping(value = "/job", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseObject> updateUser(@RequestBody JobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Job);
		requestObject.setOperation(RequestType.update);
		GenericResponseObject responseObject = jobService.update(requestObject);
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
	

}