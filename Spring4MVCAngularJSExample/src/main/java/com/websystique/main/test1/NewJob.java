package com.websystique.main.test1;

public class NewJob implements OVJob{
	
	String jobName="";
	
	String groupName="";
	
	public String schedulerStatus;

	OVJobListener listener;
	
	public String getSchedulerStatus() {
			return schedulerStatus;
		}


		public void setSchedulerStatus(String schedulerStatus) {
			this.schedulerStatus = schedulerStatus;
		}

		public void setOVJobListener(OVJobListener listener) {
			this.listener = listener;
		}


	@Override
	public void execute(OVJobContext context) throws Exception {
		
		System.out.println("Executing New Job");
		this.schedulerStatus="running";
		//throw new Exception();
		context.putAsString("hello", 1000);
//		TestBean testBean = new TestBean();
		//testBean.setSchedulerStatus(true);
		
	}

	@Override
	public void setjobName(String jobName) {
		this.jobName="jobName";

	}

	@Override
	public void setGroupName(String groupName) {
		this.groupName="jobgroupName";
		
	}

	@Override
	public String getJobName() {
	
		return jobName;
	}

	@Override
	public String getGroupName() {
	
		return groupName;
	}

	


	public OVJobListener getOVJobListener() {

		return listener;
	}

}
