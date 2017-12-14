package com.websystique.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.request.UsdtJobRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.service.BittrexService;

@RestController
@RequestMapping("/api/bittrex")
public class BittrexController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexController.class);
	
	@Autowired
	BittrexService bittrexService; // Service which will do all data
								// retrieval/manipulation work



	
	// -------------------Retrieve Total ------------------------
	@RequestMapping(value = "/averagetotal", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getAverageTotal() {
		GenericResponseObject responseObject = bittrexService.getTotal(new GenericRequestObject(RequestType.read, ObjectType.UsdtAverageTotal, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	// ------------------- Update Bittrex Job
		// --------------------------------------------------------

	@RequestMapping(value = "/job", method = RequestMethod.POST)
	public ResponseEntity<GenericResponseObject> createJob(@RequestBody UsdtJobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Usdt);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = bittrexService.createJob(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getJob() {
		GenericResponseObject responseObject = bittrexService.startJob(new GenericRequestObject(RequestType.start, ObjectType.Usdt, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/job", method = RequestMethod.GET)
//	public ResponseEntity<GenericResponseObject> getJob() {
//		GenericResponseObject responseObject = bittrexService.getBittrexJob(new GenericRequestObject(RequestType.read, ObjectType.Usdt, null));
//		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
//	}

}