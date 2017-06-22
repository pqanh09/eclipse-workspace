package com.tuanOv.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tuanOv.models.RestResponseGeneral;
import com.tuanOv.models.RestResponseGeneralBuilder;

@Service
public class UltilityService {
	
	public static ResponseEntity<RestResponseGeneral> getErrorResponseEntityByMessage(String message) {
		RestResponseGeneral result;
		
		result = new RestResponseGeneralBuilder()
						.setMessage(message)
						.buildErrorResponse();
		return new ResponseEntity<RestResponseGeneral>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity<RestResponseGeneral> getSuccessResponseEntityByData(Object data) {
		RestResponseGeneral result;

		result = new RestResponseGeneralBuilder()
						.setData(data)
						.buildSuccessResponse();
		return new ResponseEntity<RestResponseGeneral>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity<RestResponseGeneral> getSuccessResponseEntityByMessage(String message) {
		RestResponseGeneral result;

		result = new RestResponseGeneralBuilder()
						.setMessage(message)
						.buildSuccessResponse();
		return new ResponseEntity<RestResponseGeneral>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity<RestResponseGeneral> getSuccessResponseEntityByDataAndMessage(Object data, String message) {
		RestResponseGeneral result;

		result = new RestResponseGeneralBuilder()
						.setData(data)
						.setMessage(message)
						.buildSuccessResponse();
		return new ResponseEntity<RestResponseGeneral>(result, HttpStatus.OK);
	}
}
