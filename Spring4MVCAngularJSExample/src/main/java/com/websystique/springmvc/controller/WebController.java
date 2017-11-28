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
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.request.WebRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.service.WebService;

@RestController
@RequestMapping("/api")
public class WebController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	WebService webService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Web--------------------------------------------------------

	@RequestMapping(value = "/web", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> listAllUsers() {
		GenericResponseObject responseObject = webService.findAll(new GenericRequestObject(RequestType.read, ObjectType.Web, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}


	// -------------------Create a Web

	@RequestMapping(value = "/web", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> createUser(@RequestBody WebRequestObject requestObject) {
		//LOGGER.info(requestObject.toString());
		requestObject.setObjectType(ObjectType.Web);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = webService.create(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Update a Web
	// --------------------------------------------------------

	@RequestMapping(value = "/web", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseObject> updateUser(@RequestBody WebRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Web);
		requestObject.setOperation(RequestType.update);
		GenericResponseObject responseObject = webService.update(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Delete Web(s)
	// --------------------------------------------------------

	@RequestMapping(value = "/web", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseObject> deleteUser(@RequestBody WebRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Web);
		requestObject.setOperation(RequestType.delete);
		GenericResponseObject responseObject = webService.delete(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	

}