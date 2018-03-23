
package com.comics.springmvc.service.test;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;

/**
 * job definition
 * 
 */
public interface JobSchedulerHandler {
    /**
     * deliver message to real handler
     * 
     * @param jobParams
     * @throws Exception
     */
    public void process(JobDataMap jobParams) throws Exception;  
    
    public void processFail(JobDataMap jobParams, JobExecutionException jobException) throws Exception;
}
