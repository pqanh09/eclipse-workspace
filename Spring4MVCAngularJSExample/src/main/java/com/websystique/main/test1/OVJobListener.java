package com.websystique.main.test1;

public interface OVJobListener {
    
	   void onJobStart(OVJobContext context);

	   void onJobComplete(OVJobContext context);

	   void onJobFailed(OVJobContext context);

	} 
