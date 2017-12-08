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
import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.poller.ComicPoller;
import com.websystique.springmvc.repositories.JobRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.JobRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.JobResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.PartResponseStatus;
import com.websystique.springmvc.service.test.ComicSchedulerServiceImpl;
import com.websystique.springmvc.vo.JobVO;

@Service("jobService")
public class JobServiceImpl implements JobService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(JobServiceImpl.class);
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	ComicPoller comicPoller;
	
	@Autowired 
	ComicSchedulerServiceImpl comicSchedulerServiceImpl;

	@Override
	public GenericResponseObject create(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			JobRequestObject request = (JobRequestObject)gRequest;
			JobVO jobVO = request.getModel();
			response.setUniqueName(jobVO.getName());
			// check exist
			if(jobRepository.findByName(jobVO.getName()) == null) {
				Job job = ModelUtilProvider.getModelUtil().convertTo(jobVO, Job.class);
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
	public GenericResponseObject update(GenericRequestObject gRequest) {
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
				JobVO jobVO = ModelUtilProvider.getModelUtil().convertTo(job, JobVO.class);
				jobVO.setObjectId(job.getInstanceid().toString());
				result.add(jobVO);
			}
			response.setList(result);
			LOGGER.info("@!@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.info(comicSchedulerServiceImpl.getJobList().toString());
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
				comicPoller.pollManga(request.getIds().get(0));
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
				if(!JobState.scheduled.equals(dbJob.getStatus())){
					LOGGER.error("Can't stop job. Job state: " + dbJob.getStatus().toString());
					response.setMessage("Can't stop job. Job state: " + dbJob.getStatus().toString());
					response.setSuccess(false);
					return response;
				}
				if (!comicSchedulerServiceImpl.cancelJob(request.getIds().get(0))){
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
				//force
				if(!request.isForce()){
					//check job is running or scheduled-> fail. Only start when job stopped
					if(!JobState.stop.equals(dbJob.getStatus())){
						LOGGER.error("Can't start job. Job state: " + dbJob.getStatus().toString());
						response.setMessage("Can't start job. Job state: " + dbJob.getStatus().toString());
						response.setSuccess(false);
						return response;
					}
				} 
				if (!comicSchedulerServiceImpl.schedule(dbJob)){
					LOGGER.error("Can't start job");
					response.setMessage("Can't start job");
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
