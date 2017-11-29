package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.repositories.MangaRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.MangaRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.MangaResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.PartResponseStatus;
import com.websystique.springmvc.vo.MangaVO;

@Service("mangaService")
public class MangaServiceImpl implements MangaService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(MangaServiceImpl.class);
	
	@Autowired
	private MangaRepository mangaRepository;
	
	
	@Override
	public GenericResponseObject create(GenericRequestObject gRequest) {
		MangaResponseObject response = new MangaResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			MangaRequestObject request = (MangaRequestObject)gRequest;
			MangaVO mangaVO = request.getModel();
			response.setUniqueName(mangaVO.getName());
			// check exist
			if(mangaRepository.findByName(mangaVO.getName()) != null) {
				LOGGER.error("Manga is existed");
				response.setMessage(Messages.COMMON_EXIST);
				response.setSuccess(false);
				return response;
			}
			// create Manga
			Manga manga = ModelUtilProvider.getModelUtil().convertTo(mangaVO, Manga.class);
			manga = mangaRepository.insert(manga);
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when creating Manga", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject update(GenericRequestObject gRequest) {
		MangaResponseObject response = new MangaResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			MangaRequestObject request = (MangaRequestObject)gRequest;
			MangaVO mangaVO = request.getModel();
			response.setUniqueName(mangaVO.getName());
			//check not found
			if(mangaRepository.findOne(new ObjectId(mangaVO.getObjectId())) == null){
				LOGGER.error("Web not found");
				response.setMessage(Messages.COMMON_NOT_FOUND);
				response.setSuccess(false);
				return response;
			}
			// update Manga
			Manga manga = ModelUtilProvider.getModelUtil().convertTo(mangaVO, Manga.class);
			manga.setInstanceid(new ObjectId(mangaVO.getObjectId()));
			mangaRepository.safeSave(manga);
			
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when updating Manga", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject delete(GenericRequestObject gRequest) {
		MangaResponseObject response = new MangaResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			MangaRequestObject request = (MangaRequestObject)gRequest;
			List<String> ids = request.getIds();
			List<PartResponseStatus> partStatus = new ArrayList<>();
			
			for (String id : ids) {
				PartResponseStatus part = new PartResponseStatus(id, true, Messages.COMMON_SUCCESS);
				try {
					mangaRepository.delete(new ObjectId(id));
				}catch (Exception e) {
					LOGGER.error("An error when deleting Manga {}:", id, e);
					part.setMessage(Messages.COMMON_FAIL);
					part.setSuccess(false);
				}
				partStatus.add(part);
			}
			response.setPartStatus(partStatus);
		}catch (Exception e) {
			LOGGER.error("An error when delete Manga(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject findAll(GenericRequestObject request) {
		MangaResponseObject response = new MangaResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			List<MangaVO> result = new ArrayList<>();
			List<Manga> list = mangaRepository.findAll();
			
			for (Manga manga : list) {
				MangaVO mangaVO = ModelUtilProvider.getModelUtil().convertTo(manga, MangaVO.class);
				mangaVO.setObjectId(manga.getInstanceid().toString());
				result.add(mangaVO);
			}
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when reading Manga(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	
	

}
