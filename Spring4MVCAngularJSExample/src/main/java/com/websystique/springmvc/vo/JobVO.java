package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.model.JobType;

public class JobVO extends GenericVO{
	
	private JobType type;
	
	private String description;
	
	private List<String> mangas = new ArrayList<>();
	
	//time interval
	private int timeInterval = 3600;
	
	private int intervalType = Calendar.SECOND;
	
	//cron expressions
	private String cronExpression;
	
	private long timeStart;
	
	private long timeFinish;
	
	private JobState status = JobState.unknown;
	
	private int percent;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMangas() {
		return mangas;
	}

	public void setMangas(List<String> mangas) {
		this.mangas = mangas;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(long timeFinish) {
		this.timeFinish = timeFinish;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}

	public int getIntervalType() {
		return intervalType;
	}
	public void setIntervalType(int intervalType) {
		this.intervalType = intervalType;
	}

	public JobState getStatus() {
		return status;
	}

	public void setStatus(JobState status) {
		this.status = status;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
