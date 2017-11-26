package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Manga.COLLECTION_NAME)
public class Manga extends GenericModel<ObjectId>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5768805756407588844L;

	
	public static final String COLLECTION_NAME = "Manga";
	
	@NotNull
	@Size(max=250)	
	@Indexed(unique = true)
	private String name;
	
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

	public Manga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manga(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}

	
 }
