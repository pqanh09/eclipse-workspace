package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class MangaVO {

	private String name;
	
	private ObjectId instanceid;
	
	private String author;
	
	private String latestChapterName;
	
	private int latestChapterOrdinalNumber;
	
	private String latestChapterId;
	
	//link use to collect data by default
	private String mainLink;
	
	private List<String> links = new ArrayList<> ();

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
	
	public String getMainLink() {
		return mainLink;
	}

	public void setMainLink(String mainLink) {
		this.mainLink = mainLink;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

	public int getLatestChapterOrdinalNumber() {
		return latestChapterOrdinalNumber;
	}

	public void setLatestChapterOrdinalNumber(int latestChapterOrdinalNumber) {
		this.latestChapterOrdinalNumber = latestChapterOrdinalNumber;
	}

	public ObjectId getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(ObjectId instanceid) {
		this.instanceid = instanceid;
	}

	@Override
	public String toString() {
		return "MangaVO [name=" + name + ", id=" + instanceid.toString() + ", author=" + author + ", latestChapterName="
				+ latestChapterName + ", latestChapterOrdinalNumber=" + latestChapterOrdinalNumber
				+ ", latestChapterId=" + latestChapterId + ", mainLink=" + mainLink + ", links=" + links + "]";
	}

	public MangaVO() {
		super();
	}

	
}
