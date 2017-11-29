package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

public class JobVO extends GenericVO{
	
	private String description;
	
	private List<String> mangas = new ArrayList<>();
	
	//cron expressions
	private String cronString;
	
	private long timeStart;
	
	private long timeFinish;

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

	public String getCronString() {
		return cronString;
	}

	public void setCronString(String cronString) {
		this.cronString = cronString;
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
	
}
