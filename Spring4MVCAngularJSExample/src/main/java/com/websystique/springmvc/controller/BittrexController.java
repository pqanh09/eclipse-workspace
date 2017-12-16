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
	
	@RequestMapping(value = "/startmartket", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> startmartket() {
		GenericResponseObject responseObject = bittrexService.startMartketJob(new GenericRequestObject(RequestType.start, ObjectType.Job, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/stopmartket", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> stopmartket() {
		GenericResponseObject responseObject = bittrexService.stopMartketJob(new GenericRequestObject(RequestType.start, ObjectType.Job, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/startlastprice", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> startlastprice() {
		GenericResponseObject responseObject = bittrexService.startLastPriceJob(new GenericRequestObject(RequestType.start, ObjectType.Job, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getmartket", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getmartket() {
		GenericResponseObject responseObject = bittrexService.getmartket(new GenericRequestObject(RequestType.read, ObjectType.Job, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/lastPrice", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getJob() {
		GenericResponseObject responseObject = bittrexService.getLatestLastPrice(new GenericRequestObject(RequestType.read, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
}