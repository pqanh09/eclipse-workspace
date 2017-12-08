
package com.websystique.springmvc.service.test;

import org.quartz.JobDataMap;

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
}
