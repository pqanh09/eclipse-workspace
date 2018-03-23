
package com.comics.springmvc.service.test;

import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.comics.springmvc.model.Job;
import com.comics.springmvc.model.JobHistory;
import com.comics.springmvc.model.JobState;
import com.comics.springmvc.repositories.JobHistoryRepository;
import com.comics.springmvc.repositories.JobRepository;

public abstract class AbstractJobSchedulerHandler implements JobSchedulerHandler {

	@Autowired
	protected JobRepository jobRepository;
	
	@Autowired
	protected JobHistoryRepository jobHistoryRepository;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJobSchedulerHandler.class);

    @SuppressWarnings("unchecked")
	@Override
    public void process(JobDataMap jobParams) throws Exception {
    	Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
//    	List<String> totalIds = (List<String>) jobParams.get(JobConstant.JOB_DATA_MAP_TOTAL);
//    	List<String> completedIds = new ArrayList<>();//JobConstant.JOB_DATA_MAP_COMPLETED
    	//check job null
    	if(objId == null){
    		LOGGER.error("No JobID to process.");
    		return;
    	}
    	
    	String jobId = objId.toString() ;
    	LOGGER.info("Process Job {}:", jobId);
    	Job jobDb = jobRepository.findOne(new ObjectId(jobId));
    	JobHistory jobHistory = new JobHistory();
    	jobHistory.setStartTime(Calendar.getInstance().getTime().getTime());
    	jobHistory.setJobType(jobDb.getType());
    	jobHistory.setJobId(jobId);
    	jobHistory.setSuccess(false);
    	jobHistory.setDescription("Not support this Job Type: " + jobDb.getType());
    	jobHistory.setEndTime(Calendar.getInstance().getTime().getTime());
    	//save job history
    	jobHistoryRepository.safeSave(jobHistory);
    	//update job
    	jobDb.setStatus(JobState.scheduled);
    	updateJobHistoryList(jobHistory, jobDb.getJobHistory());
    	String jobHistoryIdRemove =  jobDb.addHistory(jobHistory.getInstanceid().toString());
    	if(jobHistoryIdRemove != null) {
    		jobHistoryRepository.delete(new ObjectId(jobHistoryIdRemove));
    	}
    	jobRepository.safeSave(jobDb);
       
    }

    // delete latest history job if there is NO change
    protected void updateJobHistoryList(JobHistory newJobHistory, List<String> listJobHistory){
    	if(listJobHistory.size() > 0){
	    	String latestHistoryId = listJobHistory.get(listJobHistory.size()-1);
	    	JobHistory latestHistory = jobHistoryRepository.findOne(new ObjectId(latestHistoryId));
	    	if(latestHistory.equals(newJobHistory)){
	    		listJobHistory.remove(latestHistoryId);
	    		jobHistoryRepository.delete(latestHistory);
	    	}
    	}
    }

	@Override
	public void processFail(JobDataMap jobParams, JobExecutionException jobException) throws Exception {
		Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
    	//check job null
    	if(objId == null){
    		LOGGER.error("No JobID to process.");
    		return;
    	}
    	String jobId = objId.toString() ;
    	LOGGER.info("Process Exception in Job {}:", jobId);
    	Job jobDb = jobRepository.findOne(new ObjectId(jobId));
    	JobHistory jobHistory = new JobHistory();
    	jobHistory.setStartTime(Calendar.getInstance().getTime().getTime());
    	jobHistory.setJobType(jobDb.getType());
    	jobHistory.setJobId(jobId);
    	jobHistory.setSuccess(false);
    	jobHistory.setDescription("An expected error when executing this Job: " + jobException.getMessage() + ". Please see log file to more detail.");
    	jobHistory.setEndTime(Calendar.getInstance().getTime().getTime());
    	//save job history
    	jobHistoryRepository.safeSave(jobHistory);
    	//update job
    	jobDb.setStatus(JobState.scheduled);
    	updateJobHistoryList(jobHistory, jobDb.getJobHistory());
    	String jobHistoryIdRemove =  jobDb.addHistory(jobHistory.getInstanceid().toString());
    	if(jobHistoryIdRemove != null) {
    		jobHistoryRepository.delete(new ObjectId(jobHistoryIdRemove));
    	}
    	jobRepository.safeSave(jobDb);
	}
	
//    public abstract void execute(JobDataMap jobParams);
//
//    public abstract void pause(JobDataMap jobParams);
//
//    public abstract void stop(JobDataMap jobParams);
//
//    public abstract void resume(JobDataMap jobParams);
//
//    public abstract void start(JobDataMap jobParams);
       
}
