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
	
	private double latestChapterOrdinalNumber = -1.0;
	
	private String latestChapterId;
	
	//link use to collect data by default
	private String mainLinkName;
	
	private List<Link> links = new ArrayList<> ();
	
	//Main image for Manga
	private String imageUrl;

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

	public double getLatestChapterOrdinalNumber() {
		return latestChapterOrdinalNumber;
	}

	public void setLatestChapterOrdinalNumber(double latestChapterOrdinalNumber) {
		this.latestChapterOrdinalNumber = latestChapterOrdinalNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Manga() {
		super();
	}

	public Manga(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
	}

	
 }
