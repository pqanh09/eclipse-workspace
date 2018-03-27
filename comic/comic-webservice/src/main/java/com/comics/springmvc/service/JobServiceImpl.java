package com.comics.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.comics.shared.common.JobState;
import com.comics.shared.common.JobType;
import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.request.JobRequestObject;
import com.comics.shared.response.GenericResponseObject;
import com.comics.shared.response.JobResponseObject;
import com.comics.shared.response.Messages;
import com.comics.shared.response.PartResponseStatus;
import com.comics.shared.vo.JobVO;
import com.comics.shared.vo.MangaJobVO;
import com.comics.springmvc.model.Job;
import com.comics.springmvc.model.MangaJob;
import com.comics.springmvc.model.ModelUtilProvider;
import com.comics.springmvc.poller.MangaPoller;
import com.comics.springmvc.repositories.JobRepository;
import com.comics.springmvc.service.test.MangaSchedulerServiceImpl;

@Service("jobService")
public class JobServiceImpl extends AbstractServiceImpl implements JobService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(JobServiceImpl.class);
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	MangaPoller mangaPoller;
	
	@Autowired 
	MangaSchedulerServiceImpl mangaSchedulerServiceImpl;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public GenericResponseObject delete(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			List<String> ids = request.getIds();
			List<PartResponseStatus> partStatus = new ArrayList<>();
			
			for (String id : ids) {
				PartResponseStatus part = new PartResponseStatus(id, true, Messages.COMMON_SUCCESS);
				try {
					Job dbJob = jobRepository.findOne(new ObjectId(id));
					if(dbJob == null){
						LOGGER.error("Job not found");
						response.setMessage(Messages.COMMON_NOT_FOUND);
						response.setSuccess(false);
						partStatus.add(part);
						continue;
					} 
					if(!JobState.stop.equals(dbJob.getStatus())){
						LOGGER.error("Can't delete job! Job state: {}", dbJob.getStatus());
						response.setMessage("Can't delete job! Job state: " + dbJob.getStatus().toString());
						partStatus.add(part);
						continue;
					}
					jobRepository.delete(dbJob.getInstanceid());
				}catch (Exception e) {
					LOGGER.error("An error when deleting Job {}:", id, e);
					part.setMessage(Messages.COMMON_FAIL);
					part.setSuccess(false);
				}
				partStatus.add(part);
			}
			
			response.setPartStatus(partStatus);
		}catch (Exception e) {
			LOGGER.error("An error when delete Job(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject findAll(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			List<JobVO> result = new ArrayList<>();
			List<Job> list = jobRepository.findAll();
			
			for (Job job : list) {
				JobVO jobVO = null;
				if(job instanceof MangaJob){
					jobVO = ModelUtilProvider.getModelUtil().convertTo(job, MangaJobVO.class); 
				} else {
					continue;
				}
				jobVO.setObjectId(job.getInstanceid().toString());
				result.add(jobVO);
			}
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when reading Jobs", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject pollManga(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			if(!request.getIds().isEmpty()){
				mangaPoller.pollManga(request.getIds().get(0));
			} else {
				response.setMessage("No Manga to poll");
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
	public GenericResponseObject stopJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			if(!request.getIds().isEmpty()){
				Job dbJob = jobRepository.findOne(new ObjectId(request.getIds().get(0)));
				//check Job not found
				if(dbJob == null){
					LOGGER.error("Job not found");
					response.setMessage(Messages.COMMON_NOT_FOUND);
					response.setSuccess(false);
					return response;
				}
				//check job is running or stop or unknown  -> fail. Only start when job scheduled
				if(JobState.stop.equals(dbJob.getStatus()) || JobState.unknown.equals(dbJob.getStatus())){
					LOGGER.error("Can't stop job. Job state: " + dbJob.getStatus().toString());
					response.setMessage("Can't stop job. Job state: " + dbJob.getStatus().toString());
					response.setSuccess(false);
					return response;
				}
				if (!mangaSchedulerServiceImpl.stopJob(request.getIds().get(0), JobState.stop)){
					LOGGER.error("Can't stop job");
					response.setMessage("Can't stop job");
					response.setSuccess(false);
				}
			} else {
				response.setMessage("No job to stop");
				response.setSuccess(false);
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when stopping Job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject startJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			if(!request.getIds().isEmpty()){
				Job dbJob = jobRepository.findOne(new ObjectId(request.getIds().get(0)));
				//check Job not found
				if(dbJob == null){
					LOGGER.error("Job not found");
					response.setMessage(Messages.COMMON_NOT_FOUND);
					response.setSuccess(false);
					return response;
				}
				//check job is running or scheduled-> fail. Only start when job stopped or fail
				if(!JobState.stop.equals(dbJob.getStatus())){
					LOGGER.error("Can't start job. Job state: " + dbJob.getStatus().toString());
					response.setMessage("Can't start job. Job state: " + dbJob.getStatus().toString());
					response.setSuccess(false);
					return response;
				}
				if(JobType.Comic.equals(dbJob.getType())){
					if (!mangaSchedulerServiceImpl.startJob(dbJob)){
						LOGGER.error("Can't start job");
						response.setMessage("Can't start job");
						response.setSuccess(false);
						return response;
					}
				} else {
					LOGGER.error("Not support poll with this Job Type {}", dbJob.getType());
					response.setMessage("Not support poll with this Job Type " + dbJob.getType());
					response.setSuccess(false);
					return response;
				}
				
				
			} else {
				response.setMessage("No job to start");
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

}
