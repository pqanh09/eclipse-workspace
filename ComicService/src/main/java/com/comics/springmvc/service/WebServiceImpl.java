package com.comics.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comics.springmvc.model.ModelUtilProvider;
import com.comics.springmvc.model.Web;
import com.comics.springmvc.repositories.WebRepository;
import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.request.WebRequestObject;
import com.comics.springmvc.response.GenericResponseObject;
import com.comics.springmvc.response.Messages;
import com.comics.springmvc.response.PartResponseStatus;
import com.comics.springmvc.response.WebResponseObject;
import com.comics.springmvc.vo.WebVO;

@Service("webService")
public class WebServiceImpl implements WebService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(WebServiceImpl.class);
	@Autowired
	private WebRepository webRepository;
	

	@Override
	public GenericResponseObject create(GenericRequestObject gRequest) {
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			WebRequestObject request = (WebRequestObject)gRequest;
			WebVO webVO = request.getModel();
			response.setUniqueName(webVO.getName());
			// check exist
			if(webRepository.findByName(webVO.getName()) == null) {
				Web web = ModelUtilProvider.getModelUtil().convertTo(webVO, Web.class);
				webRepository.safeSave(web);
			} else {
				LOGGER.error("Web is existed");
				response.setMessage(Messages.COMMON_EXIST);
				response.setSuccess(false);
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
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
			WebRequestObject request = (WebRequestObject)gRequest;
			WebVO webVO = request.getModel();
			response.setUniqueName(webVO.getName());
			//check not found
			if(webRepository.findOne(new ObjectId(webVO.getObjectId())) == null){
				LOGGER.error("Web not found");
				response.setMessage(Messages.COMMON_NOT_FOUND);
				response.setSuccess(false);
				return response;
			}
			//set ObjectId
			Web web = ModelUtilProvider.getModelUtil().convertTo(webVO, Web.class);
			web.setInstanceid(new ObjectId(webVO.getObjectId()));
			webRepository.safeSave(web);
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
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
			List<PartResponseStatus> partStatus = new ArrayList<>();
			
			for (String id : ids) {
				PartResponseStatus part = new PartResponseStatus(id, true, Messages.COMMON_SUCCESS);
				try {
					webRepository.delete(new ObjectId(id));
				}catch (Exception e) {
					LOGGER.error("An error when deleting Web {}:", id, e);
					part.setMessage(Messages.COMMON_FAIL);
					part.setSuccess(false);
				}
				partStatus.add(part);
			}
			
			response.setPartStatus(partStatus);
		}catch (Exception e) {
			LOGGER.error("An error when delete Web(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject findAll(GenericRequestObject gRequest) {
		WebResponseObject response = new WebResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			List<WebVO> result = new ArrayList<>();
			List<Web> webList = webRepository.findAll();
			
			for (Web web : webList) {
				WebVO webVO = ModelUtilProvider.getModelUtil().convertTo(web, WebVO.class);
				webVO.setObjectId(web.getInstanceid().toString());
				result.add(webVO);
			}
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when reading Webs", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

}
