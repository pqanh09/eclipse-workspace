package com.websystique.main.test1;

public interface OVJob {
	
    void execute(OVJobContext context)throws Exception;
    
    void setjobName(String jobName);
    
    void setGroupName(String groupName);

	String getJobName();

	String getGroupName();
	
    OVJobListener getOVJobListener();
    
}

