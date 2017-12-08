
package com.websystique.springmvc.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.types.ObjectId;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobHistory;
import com.websystique.springmvc.model.JobHistoryDetail;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.poller.ComicPoller;
import com.websystique.springmvc.repositories.JobHistoryRepository;
import com.websystique.springmvc.repositories.JobRepository;

@Service("comicJobSchedulerHandler")
public class ComicJobSchedulerHandler extends AbstractJobSchedulerHandler{

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private JobHistoryRepository jobHistoryRepository;
	
	@Autowired
	private ComicPoller comicPoller;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ComicJobSchedulerHandler.class);

    @Override
    public void process(JobDataMap jobParams) throws Exception {
    	Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
//    	List<String> totalIds = (List<String>) jobParams.get(JobConstant.JOB_DATA_MAP_TOTAL);
//    	List<String> completedIds = new ArrayList<>();//JobConstant.JOB_DATA_MAP_COMPLETED
    	// TODO check job null
    	if(objId == null){
    	}
    	
    	String jobId = objId.toString() ;
    	LOGGER.info("Process Job {}:", jobId);
    	Job jobDb = jobRepository.findOne(new ObjectId(jobId));
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
    		JobHistoryDetail jobDetail = comicPoller.pollManga(mangaId);
    		jobDetails.add(jobDetail);
    		if(jobDetail.isSuccess()){
    			completedIds.add(mangaId);
    		}
		}
    	//save job history
    	jobHistory.setEndTime(Calendar.getInstance().getTime().getTime());
    	jobHistoryRepository.safeSave(jobHistory);
    	//update job
    	jobDb.setStatus(JobState.scheduled);
    	String jobHistoryIdRemove =  jobDb.addHistory(jobHistory.getInstanceid().toString());
    	if(jobHistoryIdRemove != null) {
    		jobHistoryRepository.delete(new ObjectId(jobHistoryIdRemove));
    	}
    	System.out.println("Run job completly");
    	jobRepository.safeSave(jobDb);
    }
    
	@Override
	public void execute(JobDataMap jobParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(JobDataMap jobParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(JobDataMap jobParams) {
		System.out.println("ComicJobSchedulerHandler STOPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(JobDataMap jobParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(JobDataMap jobParams) {
		// TODO Auto-generated method stub
		
	}
    
    
}
