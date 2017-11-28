package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Link;
import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.model.Web;
import com.websystique.springmvc.repositories.ChapterRepository;
import com.websystique.springmvc.repositories.LinkRepository;
import com.websystique.springmvc.repositories.MangaRepository;
import com.websystique.springmvc.repositories.WebRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.MangaRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.MangaResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.PartResponseStatus;
import com.websystique.springmvc.vo.LinkVO;
import com.websystique.springmvc.vo.MangaVO;

@Service("mangaService")
public class MangaServiceImpl implements MangaService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(MangaServiceImpl.class);
	
	@Autowired
	private MangaRepository mangaRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	private List<Link> saveLinkVO(List<LinkVO> linkVOs){
		List<Link> results = new ArrayList<>();
		for (LinkVO linkVO : linkVOs) {
			try {
				Link link =  ModelUtilProvider.getModelUtil().convertTo(linkVO, Link.class);
				//update
				if(StringUtils.isNotBlank(linkVO.getObjectId())){
					link.setInstanceid(new ObjectId(linkVO.getObjectId()));
					linkRepository.safeSave(link);
				}
				// create
				else {
					link = linkRepository.insert(link);
				}
				results.add(link);
			}catch (Exception e) {
				LOGGER.info("Link: {}", linkVO.toString());
				LOGGER.error("An error when save Link:", e);
			}
		}
		return results;
	}
	@Override
	public GenericResponseObject create(GenericRequestObject gRequest) {
		MangaResponseObject response = new MangaResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			MangaRequestObject request = (MangaRequestObject)gRequest;
			MangaVO mangaVO = request.getMaga();
			response.setUniqueName(mangaVO.getName());
			List<LinkVO> linkVOs = request.getLinks();
			//save Links
			List<Link> links = saveLinkVO(linkVOs);
			//get main Link
			String mainLinkName = mangaVO.getMainLinkName();
			if(StringUtils.isBlank(mainLinkName)){
				response.setMessage("No Main Link Name");
				response.setSuccess(true);
				return response;
			}
			Link mainLink = null;
			List<String> linkIds = new ArrayList<>();
			for (Link link : links) {
				linkIds.add(link.getInstanceid().toString());
				if(link.getName().equals(mainLinkName)){
					mainLink = link;
				}
			}
			if(mainLink == null){
				response.setMessage("No Main Link");
				response.setSuccess(true);
				return response;
			}
			// check exist
			if(mangaRepository.findByName(mangaVO.getName()) != null) {
				LOGGER.error("Manga is existed");
				response.setMessage(Messages.COMMON_EXIST);
				response.setSuccess(false);
				return response;
			}
			// create Manga
			Manga manga = ModelUtilProvider.getModelUtil().convertTo(mangaVO, Manga.class);
			manga.setLinks(linkIds);
			manga.setMainLinkId(mainLink.getInstanceid().toString());
			manga = mangaRepository.insert(manga);
			//update mangaId in Links
			for (Link link : links) {
				if(StringUtils.isBlank(link.getMangaId())){
					link.setMangaId(manga.getInstanceid().toString());
					linkRepository.safeSave(link);
				}
			}
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
			MangaVO mangaVO = request.getMaga();
			response.setUniqueName(mangaVO.getName());
			List<LinkVO> linkVOs = request.getLinks();
			//save Links
			List<Link> links = saveLinkVO(linkVOs);
			//get main Link
			String mainLinkName = mangaVO.getMainLinkName();
			if(StringUtils.isBlank(mainLinkName)){
				response.setMessage("No Main Link Name");
				response.setSuccess(true);
				return response;
			}
			Link mainLink = null;
			List<String> linkIds = new ArrayList<>();
			for (Link link : links) {
				linkIds.add(link.getInstanceid().toString());
				if(link.getName().equals(mainLinkName)){
					mainLink = link;
				}
			}
			if(mainLink == null){
				response.setMessage("No Main Link");
				response.setSuccess(true);
				return response;
			}
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
			manga.setLinks(linkIds);
			manga.setMainLinkId(mainLink.getInstanceid().toString());
			mangaRepository.safeSave(manga);
			//update mangaId in Links
			for (Link link : links) {
				if(StringUtils.isBlank(link.getMangaId())){
					link.setMangaId(manga.getInstanceid().toString());
					linkRepository.safeSave(link);
				}
			}
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
					//delete related links
					Criteria criteria = Criteria.where(Link.ATTR_MANGA_ID).is(id);
					mangaRepository.removeByCriteria(criteria, Manga.class);
					//TODO delete related Jobs
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
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
