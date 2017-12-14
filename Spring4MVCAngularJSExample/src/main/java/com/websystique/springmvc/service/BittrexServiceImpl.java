package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.model.JobType;
import com.websystique.springmvc.model.ModelUtilProvider;
import com.websystique.springmvc.model.UsdtJob;
import com.websystique.springmvc.model.UsdtTotal;
import com.websystique.springmvc.repositories.JobRepository;
import com.websystique.springmvc.repositories.UsdtTotalRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.UsdtJobRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.JobResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.UsdtTotalResponseObject;
import com.websystique.springmvc.service.test.BittrexSchedulerServiceImpl;
import com.websystique.springmvc.vo.UsdtJobVO;
import com.websystique.springmvc.vo.UsdtTotalVO;

@Service("bittrexService")
public class BittrexServiceImpl extends AbstractServiceImpl implements BittrexService{
	
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexServiceImpl.class);
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UsdtTotalRepository usdtTotalRepository;

	@Autowired
	private BittrexSchedulerServiceImpl bittrexSchedulerServiceImpl; 
	

	@Override
	public GenericResponseObject getTotal(GenericRequestObject request) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			Date dtmp = new Date();
			Date date = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			long time = date.getTime();
//			Criteria criteria = Criteria.where(UsdtTotal.ATTR_TIME).gte(time);
//			List<UsdtTotal> list = usdtTotalRepository.searchByCriteria(criteria, UsdtTotal.class);
			UsdtTotal usdtTotal = usdtTotalRepository.findByTime(time);
			List<UsdtTotalVO> result = new ArrayList<>();
			if(usdtTotal != null){
				result.add(ModelUtilProvider.getModelUtil().convertTo(usdtTotal, UsdtTotalVO.class));
			}
			
			response.setList(result);
		}catch (Exception e) {
			LOGGER.error("An error when getting total", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	
	@Override
	public GenericResponseObject createJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtJobRequestObject request = (UsdtJobRequestObject)gRequest;
			UsdtJobVO jobVO = request.getModel();
			response.setUniqueName(jobVO.getName());
			//check running or schedule. Only one job can run
			Criteria criteriaStatus1 = Criteria.where(Job.ATT_JOB_STATE).in(Arrays.asList(JobState.running, JobState.scheduled));
			Criteria criteria1 = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.Bittrex).andOperator(criteriaStatus1);
			
			List<Job> result = jobRepository.searchByCriteria(criteria1, Job.class);
			if(result != null && !result.isEmpty()) {
				LOGGER.error("Please stop current Bittrex Job before creating new Job.");
				response.setMessage("Please stop current Bittrex Job before creating new Job.");
				response.setSuccess(false);
				return response;
			}
			// check exist
			Criteria criteriaStatus = Criteria.where(Job.ATT_JOB_STATE).is(JobState.stop);
			Criteria criteria = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.Bittrex).andOperator(criteriaStatus);
			
			result = jobRepository.searchByCriteria(criteria, Job.class);
			if(result == null || result.isEmpty()) {
				UsdtJob job = ModelUtilProvider.getModelUtil().convertTo(jobVO, UsdtJob.class);
				job.setStatus(JobState.stop);
				jobRepository.safeSave(job);
			} else {
				LOGGER.error("Already have Bittrex Job to start");
				response.setMessage("Already have Bittrex Job to start");
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
	public GenericResponseObject startJob(GenericRequestObject gRequest) {
		JobResponseObject response = new JobResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			// check exist
			Criteria criteriaStatus = Criteria.where(Job.ATT_JOB_STATE).is(JobState.stop);
			Criteria criteria = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.Bittrex).andOperator(criteriaStatus);
			
			List<Job> result = jobRepository.searchByCriteria(criteria, Job.class);
			if(result == null || result.isEmpty()) {
				LOGGER.error("No Bittrex Job to start");
				response.setMessage("No Bittrex Job to start");
				response.setSuccess(false);
			} else {
				if (!bittrexSchedulerServiceImpl.startJob(result.get(0))){
					LOGGER.error("Can't start job");
					response.setMessage("Can't start job");
					response.setSuccess(false);
					return response;
				}
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when starting job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

}
