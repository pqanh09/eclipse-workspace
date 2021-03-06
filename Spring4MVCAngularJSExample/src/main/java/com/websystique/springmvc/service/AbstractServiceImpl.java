package com.websystique.springmvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.Messages;

public abstract class AbstractServiceImpl implements GenericService{
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(AbstractServiceImpl.class);
	
	@Override
	public GenericResponseObject create(GenericRequestObject gRequest) {
		LOGGER.info("Request: {}", gRequest.toString());
		LOGGER.error("Not support this operation");
		GenericResponseObject response = new GenericResponseObject(gRequest);
		response.setMessage(Messages.COMMON_NOT_SUPPORT);
		response.setSuccess(false);
		return response;
	}

	@Override
	public GenericResponseObject update(GenericRequestObject gRequest) {
		LOGGER.info("Request: {}", gRequest.toString());
		LOGGER.error("Not support this operation");
		GenericResponseObject response = new GenericResponseObject(gRequest);
		response.setMessage(Messages.COMMON_NOT_SUPPORT);
		response.setSuccess(false);
		return response;
	}

	@Override
	public GenericResponseObject delete(GenericRequestObject gRequest) {
		LOGGER.info("Request: {}", gRequest.toString());
		LOGGER.error("Not support this operation");
		GenericResponseObject response = new GenericResponseObject(gRequest);
		response.setMessage(Messages.COMMON_NOT_SUPPORT);
		response.setSuccess(false);
		return response;
	}
	
	@Override
	public GenericResponseObject findAll(GenericRequestObject gRequest) {
		LOGGER.info("Request: {}", gRequest.toString());
		LOGGER.error("Not support this operation");
		GenericResponseObject response = new GenericResponseObject(gRequest);
		response.setMessage(Messages.COMMON_NOT_SUPPORT);
		response.setSuccess(false);
		return response;
	}
}
