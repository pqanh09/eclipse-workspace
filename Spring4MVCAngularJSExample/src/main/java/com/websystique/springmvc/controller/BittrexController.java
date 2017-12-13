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
import com.websystique.springmvc.service.BittrexService;

@RestController
@RequestMapping("/api/bittrex")
public class BittrexController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexController.class);
	
	@Autowired
	BittrexService bittrexService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// ittrex--------------------------------------------------------

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> listAllUsers() {
		GenericResponseObject responseObject = bittrexService.getInput(new GenericRequestObject(RequestType.read, ObjectType.BittrexInput, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}


	// ------------------- Update a ittrex
	// --------------------------------------------------------

	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public ResponseEntity<GenericResponseObject> updateUser(@RequestBody WebRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.BittrexInput);
		requestObject.setOperation(RequestType.update);
		GenericResponseObject responseObject = bittrexService.updateInput(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

}