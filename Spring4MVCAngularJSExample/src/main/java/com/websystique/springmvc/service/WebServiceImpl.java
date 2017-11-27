package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.model.Web;
import com.websystique.springmvc.repositories.WebRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.WebRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.WebResponseObject;
import com.websystique.springmvc.vo.WebVO;

@Service("webService")
public class WebServiceImpl implements WebService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(WebServiceImpl.class);
	@Autowired
	private WebRepository webRepository;
	

	@Override
	public GenericResponseObject save(GenericRequestObject gRequest) {
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			//TODO check exist
			WebRequestObject request = (WebRequestObject)gRequest;
			response.setUniqueName(request.getWeb().getName());
			Web web = ModelUtilProvider.getModelUtil().convertTo(request.getWeb(), Web.class);
			webRepository.safeSave(web);
		}catch (Exception e) {
			LOGGER.error("An error when creating Web", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject update(GenericRequestObject gRequest) {
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			//TODO check not found
			//TODO set ObjectId
			WebRequestObject request = (WebRequestObject)gRequest;
			response.setUniqueName(request.getWeb().getName());
			Web web = ModelUtilProvider.getModelUtil().convertTo(request.getWeb(), Web.class);
//			web.setInstanceid(instanceid);
			webRepository.safeSave(web);
		}catch (Exception e) {
			LOGGER.error("An error when udpating Web", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject delete(GenericRequestObject gRequest) {
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			WebRequestObject request = (WebRequestObject)gRequest;
			List<String> ids = request.getIds();
			for (String id : ids) {
				webRepository.delete(new ObjectId(id));
			}
			//response.setUniqueName("");
		}catch (Exception e) {
			LOGGER.error("An error when delete Web(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject findAll(GenericRequestObject gRequest) {
		// TODO Auto-generated method stub
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			List<WebVO> result = new ArrayList<>();
			List<Web> temp = webRepository.findAll();
			
			for (Web web : temp) {
				result.add(ModelUtilProvider.getModelUtil().convertTo(web, WebVO.class));
			}
			response.setList(result);
			//response.setUniqueName("");
		}catch (Exception e) {
			LOGGER.error("An error when reading Webs", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

}
