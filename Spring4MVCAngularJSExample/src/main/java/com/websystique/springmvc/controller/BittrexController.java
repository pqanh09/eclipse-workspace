package com.websystique.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.request.UsdtTotalRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.service.BittrexService;

@RestController
@RequestMapping("/api/bittrex")
public class BittrexController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexController.class);
	
	@Autowired
	BittrexService bittrexService; // Service which will do all data
								// retrieval/manipulation work

	
	//////////////////////
	//Last Price data
	@RequestMapping(value = "/getLatestLastPriceData", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getJob() {
		GenericResponseObject responseObject = bittrexService.getLatestLastPriceData(new GenericRequestObject(RequestType.read, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	//////////////////
	
	
	
	//Total
	//get by Id
	@RequestMapping(value = "/total/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getJob(@PathVariable("id") String id) {
		UsdtTotalRequestObject requestObject = new UsdtTotalRequestObject();
		requestObject.getIds().add(id);
		requestObject.setObjectType(ObjectType.UsdtAverageTotal);
		requestObject.setOperation(RequestType.read);
		GenericResponseObject responseObject = bittrexService.getTotalById(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	//get all
	@RequestMapping(value = "/total", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getAllTotals() {
		GenericResponseObject responseObject = bittrexService.getAllTotals(new GenericRequestObject(RequestType.read, ObjectType.UsdtAverageTotal, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	//create
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public ResponseEntity<GenericResponseObject> createTotal(@RequestBody UsdtTotalRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.UsdtAverageTotal);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = bittrexService.createTotal(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	//update -> remove all total, percent
	@RequestMapping(value = "/total", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseObject> updateTotal(@RequestBody UsdtTotalRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.UsdtAverageTotal);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = bittrexService.updateTotal(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	//delete
	@RequestMapping(value = "/total", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseObject> deleteTotal(@RequestBody UsdtTotalRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.UsdtAverageTotal);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = bittrexService.deleteTotal(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	///////////////////////
	//Last Price Job
	
	@RequestMapping(value = "/job/getLastPriceJob", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getLastPriceJob() {
		GenericResponseObject responseObject = bittrexService.getLastPriceJob(new GenericRequestObject(RequestType.read, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/job/createLastPriceJob", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> createlastprice() {
		GenericResponseObject responseObject = bittrexService.createLastPriceJob(new GenericRequestObject(RequestType.create, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/job/startLastPriceJob", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> startLastPriceJob() {
		GenericResponseObject responseObject = bittrexService.startLastPriceJob(new GenericRequestObject(RequestType.start, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	@RequestMapping(value = "/job/stopLastPriceJob", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> stopLastPriceJob() {
		GenericResponseObject responseObject = bittrexService.stopLastPriceJob(new GenericRequestObject(RequestType.stop, ObjectType.UsdtLastPrice, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
}