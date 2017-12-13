package com.websystique.springmvc.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.model.UsdtInput;
import com.websystique.springmvc.repositories.UsdtInputRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.UsdtInputRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.UsdtInputResponseObject;
import com.websystique.springmvc.vo.UsdtInputVO;

@Service("bittrexService")
public class BittrexServiceImpl extends AbstractServiceImpl implements BittrexService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexServiceImpl.class);
	
	@Autowired
	private UsdtInputRepository usdtInputRepository;
	

	@Override
	public GenericResponseObject getInput(GenericRequestObject gRequest) {
		UsdtInputResponseObject response = new UsdtInputResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
		
			List<UsdtInput> list = usdtInputRepository.findAll();
			UsdtInputVO result = null;
			if(list != null && !list.isEmpty()){
				result = ModelUtilProvider.getModelUtil().convertTo(list.get(0), UsdtInputVO.class);
			} else {
				long time  = (new Date()).getTime();
				UsdtInput input = new UsdtInput(time, Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
				result = new UsdtInputVO(time, Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
				usdtInputRepository.save(input);
			}
			
			response.setList(Arrays.asList(result));
		}catch (Exception e) {
			LOGGER.error("An error when reading Input", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject updateInput(GenericRequestObject gRequest) {
		UsdtInputResponseObject response = new UsdtInputResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtInputRequestObject request = (UsdtInputRequestObject)gRequest;
			UsdtInputVO inputVO = request.getModel();
			
			List<UsdtInput> list = usdtInputRepository.findAll();
			UsdtInput input = ModelUtilProvider.getModelUtil().convertTo(inputVO, UsdtInput.class);
			
			input.setTime((new Date()).getTime());
			
			ObjectId objectId = null;
			if(list != null && !list.isEmpty()){
				objectId = list.get(0).getInstanceid();
				input.setInstanceid(objectId);
			} 
			usdtInputRepository.safeSave(input);
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when udpating Input", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}


}
