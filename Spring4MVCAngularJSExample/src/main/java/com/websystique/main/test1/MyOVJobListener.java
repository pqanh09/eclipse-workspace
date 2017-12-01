package com.websystique.main.test1;

public class MyOVJobListener implements OVJobListener{

	public void onJobStart(OVJobContext context) {
		
		System.out.println("jobToBeExecuted");
		System.out.println("value of hello"+context.getBooleanValue("hi"));
	
	}

	public void onJobComplete(OVJobContext context) {
		System.out.println("jobcomplete");
		System.out.println("counter value is>>>"+context.getIntFromString("counter"));
	}

	public void onJobFailed(OVJobContext context) {
		System.out.println("jobfailed due to the following"+context.get("ErrorMsg"));
		 
		
	}
 
		
	}


