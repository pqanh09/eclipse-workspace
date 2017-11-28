package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.Link;

public class MangaVO {

	private String name;
	
	private String objectId;
	
	private String author;
	
	private String latestChapterName;
	
	private int latestChapterOrdinalNumber;
	
	private String latestChapterId;
	
	//link use to collect data by default
	private String mainLinkName;
	
	private List<Link> links = new ArrayList<> ();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLatestChapterName() {
		return latestChapterName;
	}

	public void setLatestChapterName(String latestChapterName) {
		this.latestChapterName = latestChapterName;
	}

	public String getLatestChapterId() {
		return latestChapterId;
	}

	public void setLatestChapterId(String latestChapterId) {
		this.latestChapterId = latestChapterId;
	}
	
	public String getMainLinkName() {
		return mainLinkName;
	}

	public void setMainLinkName(String mainLinkName) {
		this.mainLinkName = mainLinkName;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public int getLatestChapterOrdinalNumber() {
		return latestChapterOrdinalNumber;
	}

	public void setLatestChapterOrdinalNumber(int latestChapterOrdinalNumber) {
		this.latestChapterOrdinalNumber = latestChapterOrdinalNumber;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public MangaVO() {
		super();
	}
	
}
