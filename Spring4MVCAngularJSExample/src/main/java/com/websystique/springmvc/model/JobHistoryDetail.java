package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.log.LogUtils;
import com.sun.javafx.util.Logging;

import ch.qos.logback.classic.Level;

//Detail history for every element in list of Job
//EX: for every Manga
public class JobHistoryDetail {
	private String name;
	private String elementId;
	private boolean success;
	private List<String> logs = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public void addLog(Level level, String log){
		logs.add("["+ level.toString() +"]: " + log);
	}
}
