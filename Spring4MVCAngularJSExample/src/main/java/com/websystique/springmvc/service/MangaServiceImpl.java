package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.model.MangaJob;
import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.repositories.JobRepository;
import com.websystique.springmvc.repositories.MangaRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.JobRequestObject;
import com.websystique.springmvc.request.MangaJobRequestObject;
import com.websystique.springmvc.request.MangaRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.JobResponseObject;
import com.websystique.springmvc.response.MangaResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.PartResponseStatus;
import com.websystique.springmvc.vo.JobVO;
import com.websystique.springmvc.vo.MangaJobVO;
import com.websystique.springmvc.vo.MangaVO;

@Service("mangaService")
public class MangaServiceImpl extends AbstractServiceImpl implements MangaService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(MangaServiceImpl.class);
	
	@Autowired
	private JobRepository jobRepository;
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
			mangaRepository.safeSave(manga);
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


	@Override
	public GenericResponseObject createMangaJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			MangaJobRequestObject request = (MangaJobRequestObject)gRequest;
			MangaJobVO jobVO = request.getModel();
			response.setUniqueName(jobVO.getName());
			// check exist
			if(jobRepository.findByName(jobVO.getName()) == null) {
				MangaJob job = ModelUtilProvider.getModelUtil().convertTo(jobVO, MangaJob.class);
				job.setStatus(JobState.stop);
				jobRepository.safeSave(job);
			} else {
				LOGGER.error("Job is existed");
				response.setMessage(Messages.COMMON_EXIST);
				response.setSuccess(false);
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when creating Job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	@Override
	public GenericResponseObject updateMangaJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			JobVO jobVO = request.getModel();
			response.setUniqueName(jobVO.getName());
			//check not found
			Job dbJob = jobRepository.findOne(new ObjectId(jobVO.getObjectId()));
			if(dbJob == null){
				LOGGER.error("Job not found");
				response.setMessage(Messages.COMMON_NOT_FOUND);
				response.setSuccess(false);
				return response;
			} 
			if(!JobState.stop.equals(dbJob.getStatus())){
				LOGGER.error("Can't update job! Job state: {}", dbJob.getStatus());
				response.setMessage("Can't update job! Job state: " + dbJob.getStatus().toString());
				response.setSuccess(false);
				return response;
			}
			//set ObjectId
			Job job = ModelUtilProvider.getModelUtil().convertTo(jobVO, Job.class);
			job.setStatus(dbJob.getStatus());
			job.setInstanceid(new ObjectId(jobVO.getObjectId()));
			job.addHistory(Calendar.getInstance().getTime().toString());
			jobRepository.safeSave(job);
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when udpating Job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	

}
