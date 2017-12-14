
package com.websystique.springmvc.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.MangaJob;
import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobHistory;
import com.websystique.springmvc.model.JobHistoryDetail;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.poller.MangaPoller;

@Service("mangaJobSchedulerHandler")
public class MangaJobSchedulerHandler extends AbstractJobSchedulerHandler{

	@Autowired
	private MangaPoller mangaPoller;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MangaJobSchedulerHandler.class);

    @SuppressWarnings("unchecked")
	@Override
    public void process(JobDataMap jobParams) throws Exception {
    	Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
    	//check job null
    	if(objId == null){
    		LOGGER.error("No JobID to process.");
    		return;
    	}
    	
    	String jobId = objId.toString() ;
    	LOGGER.info("Process Job {}:", jobId);
    	Job job = jobRepository.findOne(new ObjectId(jobId));
    	MangaJob jobDb = (MangaJob) job;
    	List<String> totalIds = jobDb.getMangas();
    	List<String> completedIds = new ArrayList<>();
    	List<JobHistoryDetail> jobDetails = new ArrayList<>();
    	JobHistory jobHistory = new JobHistory();
    	jobHistory.setStartTime(Calendar.getInstance().getTime().getTime());
    	jobHistory.setJobType(jobDb.getType());
    	jobHistory.setJobId(jobId);
    	jobHistory.setDescription("Execute Job " + jobDb.getName());
    	jobHistory.setCompletedElemendIds(completedIds);
    	jobHistory.setTotalElemendIds(totalIds);
    	jobHistory.setJobDetails(jobDetails);
    	for (String mangaId : totalIds) {
    		JobHistoryDetail jobDetail = mangaPoller.pollManga(mangaId);
    		jobDetails.add(jobDetail);
    		if(jobDetail.isSuccess()){
    			completedIds.add(mangaId);
    		}
		}
    	//save job history
    	jobHistory.setEndTime(Calendar.getInstance().getTime().getTime());
    	jobHistory.setSuccess(completedIds.containsAll(totalIds));
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

//	@Override
//	public void execute(JobDataMap jobParams) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pause(JobDataMap jobParams) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void stop(JobDataMap jobParams) {
//		System.out.println("ComicJobSchedulerHandler STOPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void resume(JobDataMap jobParams) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void start(JobDataMap jobParams) {
//		// TODO Auto-generated method stub
//		
//	}
    
    
}
