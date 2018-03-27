package com.comics.springmvc.service.jobnotuse;

public interface OVJob {
	
    void execute(OVJobContext context)throws Exception;
    
    void setjobName(String jobName);
    
    void setGroupName(String groupName);

	String getJobName();

	String getGroupName();
	
    MyJobListener getOVJobListener();
    
}

