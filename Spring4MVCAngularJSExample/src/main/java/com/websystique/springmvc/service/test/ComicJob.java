package com.websystique.springmvc.service.test;

import com.websystique.springmvc.service.jobnotuse.MyJobListener;
import com.websystique.springmvc.service.jobnotuse.OVJob;
import com.websystique.springmvc.service.jobnotuse.OVJobContext;

public class ComicJob implements OVJob {

	String jobName = "";

	String groupName = "";

	public String schedulerStatus;

	private String jobId;

	MyJobListener listener;

	public String getSchedulerStatus() {
		return schedulerStatus;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public MyJobListener getListener() {
		return listener;
	}

	public void setListener(MyJobListener listener) {
		this.listener = listener;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setSchedulerStatus(String schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}

	public void setOVJobListener(MyJobListener listener) {
		this.listener = listener;
	}

	@Override
	public void execute(OVJobContext context) throws Exception {
		System.out.println("Executing  Comic Job");
		this.schedulerStatus = "running";
	}

	@Override
	public void setjobName(String jobName) {
		this.jobName = "jobName";

	}

	@Override
	public void setGroupName(String groupName) {
		this.groupName = "jobgroupName";

	}

	@Override
	public String getJobName() {

		return jobName;
	}

	@Override
	public String getGroupName() {

		return groupName;
	}

	public MyJobListener getOVJobListener() {

		return listener;
	}

}
