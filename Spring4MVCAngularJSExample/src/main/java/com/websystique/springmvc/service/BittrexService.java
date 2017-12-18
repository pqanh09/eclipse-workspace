package com.websystique.springmvc.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.response.GenericResponseObject;

public interface BittrexService extends GenericService{
	
	//Last Price
	GenericResponseObject getLatestLastPriceData(GenericRequestObject request);
	GenericResponseObject createLastPriceJob(GenericRequestObject request);
	GenericResponseObject getLastPriceJob(GenericRequestObject request);
	GenericResponseObject startLastPriceJob(GenericRequestObject request);
	GenericResponseObject stopLastPriceJob(GenericRequestObject request);
	
	// Total
	
	//get by Id
	GenericResponseObject getTotalById(GenericRequestObject request);
	//get all
	GenericResponseObject getAllTotals(GenericRequestObject request);
	//create
	GenericResponseObject createTotal(GenericRequestObject request);
	//update -> remove all total, percent
	GenericResponseObject updateTotal(GenericRequestObject request);
	//delete
	GenericResponseObject deleteTotal(GenericRequestObject request);
	
	
}
