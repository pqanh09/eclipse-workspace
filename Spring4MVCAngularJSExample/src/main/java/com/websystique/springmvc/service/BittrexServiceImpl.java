package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
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
import com.websystique.springmvc.model.UsdtLastPrice;
import com.websystique.springmvc.model.UsdtTotal;
import com.websystique.springmvc.repositories.JobRepository;
import com.websystique.springmvc.repositories.UsdtLastPriceRepository;
import com.websystique.springmvc.repositories.UsdtTotalRepository;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.UsdtTotalRequestObject;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.response.JobResponseObject;
import com.websystique.springmvc.response.Messages;
import com.websystique.springmvc.response.PartResponseStatus;
import com.websystique.springmvc.response.UsdtLastPriceResponseObject;
import com.websystique.springmvc.response.UsdtTotalResponseObject;
import com.websystique.springmvc.service.test.UsdtSchedulerService;
import com.websystique.springmvc.vo.UsdtJobVO;
import com.websystique.springmvc.vo.UsdtLastPriceVO;
import com.websystique.springmvc.vo.UsdtTotalVO;

@Service("bittrexService")
public class BittrexServiceImpl extends AbstractServiceImpl implements BittrexService {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(BittrexServiceImpl.class);
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UsdtTotalRepository usdtTotalRepository;

	@Autowired
	private UsdtSchedulerService usdtSchedulerService;

	@Autowired
	private UsdtLastPriceRepository usdtLastPriceRepository;


	//Last Price data
	@Override
	public GenericResponseObject getLatestLastPriceData(GenericRequestObject gRequest) {
		UsdtLastPriceResponseObject response = new UsdtLastPriceResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			Date dtmp = new Date();
			Date dtHour = new Date(dtmp.getYear(), dtmp.getMonth(), dtmp.getDate(), dtmp.getHours(), 0, 0);
			UsdtLastPrice lastPrice = usdtLastPriceRepository.findByTime(dtHour.getTime());
			if (lastPrice == null) {
				LOGGER.error("Not found any Last Price in this hour.");
				response.setMessage("Not found any Last Price in this hour.");
				response.setSuccess(false);
			} else {
				response.setList(Arrays.asList(ModelUtilProvider.getModelUtil().convertTo(lastPrice, UsdtLastPriceVO.class)));
			}
		} catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when starting job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	//Last Price job
	
	@Override
	public GenericResponseObject getLastPriceJob(GenericRequestObject request) {
		JobResponseObject response = new JobResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
				// check running or schedule. Only one job can run
				Criteria criteriaName = Criteria.where(Job.ATT_JOB_NAME).is(JobType.GetLastPrice.toString());
				Criteria criteria = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.GetLastPrice).andOperator(criteriaName);

				List<Job> jobs = jobRepository.searchByCriteria(criteria, Job.class);
				if (jobs != null && !jobs.isEmpty()) {
					UsdtJobVO jobVO = ModelUtilProvider.getModelUtil().convertTo(jobs.get(0), UsdtJobVO.class);
					response.getList().add(jobVO);
				}
		} catch (Exception e) {
			LOGGER.info("RequestObject: {}", request.toString());
			LOGGER.error("An error when getting Last Price Job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	@Override
	public GenericResponseObject createLastPriceJob(GenericRequestObject request) {
		JobResponseObject response = new JobResponseObject(request);
		response.setMessage("Create Job successfully. Waiting to start Job");
		response.setSuccess(true);
		try {
			UsdtJob job = new UsdtJob();
			job.setUrl("https://bittrex.com/api/v2.0/pub/Markets/GetMarketSummaries?_=");
			//poll every minute
			job.setCronExpression("0 * * ? * *");
			job.setType(JobType.GetLastPrice);
			job.setName(JobType.GetLastPrice.toString());
			job.setStatus(JobState.stop);
			jobRepository.safeSave(job);
	
		} catch (Exception e) {
			LOGGER.info("RequestObject: {}", request.toString());
			LOGGER.error("An error when creating Job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	
	@Override
	public GenericResponseObject startLastPriceJob(GenericRequestObject request) {
		JobResponseObject response = new JobResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			// check exist
			Criteria criteriaName = Criteria.where(Job.ATT_JOB_NAME).is(JobType.GetLastPrice.toString());
			Criteria criteria = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.GetLastPrice).andOperator(criteriaName);
			List<Job> result = jobRepository.searchByCriteria(criteria, Job.class);
			if (result != null && !result.isEmpty()) {
				UsdtJob job = (UsdtJob) result.get(0);
				if (JobState.running.equals(job.getStatus()) || JobState.scheduled.equals(job.getStatus())) {
					LOGGER.error("Last Price Job is already started");
					response.setMessage("Last Price Job is already started");
					response.setSuccess(false);
					return response;
				} else {
					if (!usdtSchedulerService.startJob(job)) {
						response.setMessage("Can't start job. Please see log to more detail");
						response.setSuccess(false);
						return response;
					}
				}
			} else {
				LOGGER.error("Not found Last Price Job to start");
				response.setMessage("Not found Last Price Job to start");
				response.setSuccess(false);
				return response;
			}
		} catch (Exception e) {
			LOGGER.info("RequestObject: {}", request.toString());
			LOGGER.error("An error when starting job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}


	@Override
	public GenericResponseObject stopLastPriceJob(GenericRequestObject request) {
		JobResponseObject response = new JobResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			// check exist
			Criteria criteriaName = Criteria.where(Job.ATT_JOB_NAME).is(JobType.GetLastPrice.toString());
			Criteria criteria = Criteria.where(Job.ATT_JOB_TYPE).is(JobType.GetLastPrice).andOperator(criteriaName);
			List<Job> result = jobRepository.searchByCriteria(criteria, Job.class);
			if (result != null && !result.isEmpty()) {
				UsdtJob job = (UsdtJob) result.get(0);
				if (JobState.running.equals(job.getStatus()) || JobState.scheduled.equals(job.getStatus())) {
					//TODO Stop Job
					if (!usdtSchedulerService.stopJob(job.getInstanceid().toString(), JobState.stop)) {
						response.setMessage("Can't stop job. Please see log to more detail");
						response.setSuccess(false);
						return response;
					}
				} else {
					LOGGER.error("Last Price Job is NOT yet started");
					response.setMessage("Last Price Job is NOT yet started");
					response.setSuccess(false);
					return response;
				}
			} else {
				LOGGER.error("Not found Last Price Job to start");
				response.setMessage("Not found Last Price Job to start");
				response.setSuccess(false);
				return response;
			}
		} catch (Exception e) {
			LOGGER.info("RequestObject: {}", request.toString());
			LOGGER.error("An error when stoping job", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}
	
	
	//Total
	@Override
	public GenericResponseObject getTotalById(GenericRequestObject gRequest) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtTotalRequestObject request = (UsdtTotalRequestObject)gRequest;
			List<String> ids = request.getIds();
			if(ids == null || ids.isEmpty()){
				LOGGER.error("No ID to find");
				response.setMessage("No ID to find");
				response.setSuccess(false);
			} else {
				UsdtTotal usdtTotal = usdtTotalRepository.findOne(new ObjectId(ids.get(0)));
				if(usdtTotal  == null) {
					LOGGER.error(Messages.COMMON_NOT_FOUND);
					response.setMessage(Messages.COMMON_NOT_FOUND);
					response.setSuccess(false);
				} else {
					UsdtTotalVO usdtTotalVO = ModelUtilProvider.getModelUtil().convertTo(usdtTotal, UsdtTotalVO.class);
					usdtTotalVO.setObjectId(usdtTotal.getInstanceid().toString());
					response.getList().add(usdtTotalVO);
				}
			}
			
		}catch (Exception e) {
			LOGGER.error("An error when reading profile", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject getAllTotals(GenericRequestObject request) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(request);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
				List<UsdtTotal> usdtTotals = usdtTotalRepository.findAll();
				for (UsdtTotal usdtTotal : usdtTotals) {
					UsdtTotalVO usdtTotalVO = ModelUtilProvider.getModelUtil().convertTo(usdtTotal, UsdtTotalVO.class);
					usdtTotalVO.setObjectId(usdtTotal.getInstanceid().toString());
					response.getList().add(usdtTotalVO);
				}
		}catch (Exception e) {
			LOGGER.error("An error when reading profiles", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject createTotal(GenericRequestObject gRequest) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtTotalRequestObject request = (UsdtTotalRequestObject)gRequest;
			UsdtTotalVO modelVO = request.getModel();
			response.setUniqueName(modelVO.getName());
			// check exist
			if(usdtTotalRepository.findByName(modelVO.getName()) == null) {
				UsdtTotal model = ModelUtilProvider.getModelUtil().convertTo(modelVO, UsdtTotal.class);
				usdtTotalRepository.safeSave(model);
			} else {
				LOGGER.error(Messages.COMMON_EXIST);
				response.setMessage(Messages.COMMON_EXIST);
				response.setSuccess(false);
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when creating profile", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	//update -> remove all total, percent
	@Override
	public GenericResponseObject updateTotal(GenericRequestObject gRequest) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtTotalRequestObject request = (UsdtTotalRequestObject)gRequest;
			UsdtTotalVO modelVO = request.getModel();
			response.setUniqueName(modelVO.getName());
			//check not found
			if(usdtTotalRepository.findOne(new ObjectId(modelVO.getObjectId())) == null){
				LOGGER.error(Messages.COMMON_NOT_FOUND);
				response.setMessage(Messages.COMMON_NOT_FOUND);
				response.setSuccess(false);
			} else {
				UsdtTotal model = ModelUtilProvider.getModelUtil().convertTo(modelVO, UsdtTotal.class);
				//set ObjectId
				model.setInstanceid(new ObjectId(modelVO.getObjectId()));
				usdtTotalRepository.safeSave(model);
			}
		}catch (Exception e) {
			LOGGER.info("RequestObject: {}", gRequest.toString());
			LOGGER.error("An error when updating profile", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public GenericResponseObject deleteTotal(GenericRequestObject gRequest) {
		UsdtTotalResponseObject response = new UsdtTotalResponseObject(gRequest);
		response.setMessage(Messages.COMMON_SUCCESS);
		response.setSuccess(true);
		try {
			UsdtTotalRequestObject request = (UsdtTotalRequestObject)gRequest;
			List<String> ids = request.getIds();
			List<PartResponseStatus> partStatus = new ArrayList<>();
			
			for (String id : ids) {
				PartResponseStatus part = new PartResponseStatus(id, true, Messages.COMMON_SUCCESS);
				try {
					usdtTotalRepository.delete(new ObjectId(id));
				}catch (Exception e) {
					LOGGER.error("An error when deleting Profile {}:", id, e);
					part.setMessage("An error when deleting Profile " + id);
					part.setSuccess(false);
				}
				partStatus.add(part);
			}
			response.setPartStatus(partStatus);
		}catch (Exception e) {
			LOGGER.error("An error when delete Profile(s)", e);
			response.setMessage(Messages.COMMON_UNKNOWN_ERROR);
			response.setSuccess(false);
		}
		return response;
	}



}
