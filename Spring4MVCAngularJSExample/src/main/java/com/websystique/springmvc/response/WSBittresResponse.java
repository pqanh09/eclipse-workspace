package com.websystique.springmvc.response;

public class WSBittresResponse {
	private boolean triggerPoll = false;
	private boolean isRecord = false;
	public boolean isTriggerPoll() {
		return triggerPoll;
	}
	public void setTriggerPoll(boolean triggerPoll) {
		this.triggerPoll = triggerPoll;
	}
	public boolean isRecord() {
		return isRecord;
	}
	public void setRecord(boolean isRecord) {
		this.isRecord = isRecord;
	}
	public WSBittresResponse(boolean triggerPoll, boolean isRecord) {
		super();
		this.triggerPoll = triggerPoll;
		this.isRecord = isRecord;
	}
	
	
}
