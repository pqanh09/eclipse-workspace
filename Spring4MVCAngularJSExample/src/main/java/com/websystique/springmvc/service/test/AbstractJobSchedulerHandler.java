
package com.websystique.springmvc.service.test;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJobSchedulerHandler implements JobSchedulerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJobSchedulerHandler.class);

    @Override
    public void process(JobDataMap jobParams) throws Exception {

       
    }

    public abstract void execute(JobDataMap jobParams);

    public abstract void pause(JobDataMap jobParams);

    public abstract void stop(JobDataMap jobParams);

    public abstract void resume(JobDataMap jobParams);

    public abstract void start(JobDataMap jobParams);
       
}
