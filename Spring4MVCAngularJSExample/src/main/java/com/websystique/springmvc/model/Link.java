package com.websystique.springmvc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Link.COLLECTION_NAME)
public class Link extends GenericModel<ObjectId>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5334169273541720185L;


	public static final String COLLECTION_NAME = "Link";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_MANGA_ID = "mangaId";
	
	@NotNull
	@Size(max=250)	
	@Indexed(unique = true)
	private String name;
	
	private String mangaId;
	//from web????
	private String webId;
	
	// main link to get all Chapters
	private String mangaUrl;
	
	//xpath to get Chapter Name
	private String xpathChapterName;
	
	//xpath to get Chapter
	private String xpathChapters;
	
	//xpath to get image in chapter
	private String xpahtImgs;

	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	public String getMangaUrl() {
		return mangaUrl;
	}

	public void setMangaUrl(String mangaUrl) {
		this.mangaUrl = mangaUrl;
	}

	public String getXpathChapters() {
		return xpathChapters;
	}

	public void setXpathChapters(String xpathChapters) {
		this.xpathChapters = xpathChapters;
	}

	public String getXpahtImgs() {
		return xpahtImgs;
	}

	public void setXpahtImgs(String xpahtImgs) {
		this.xpahtImgs = xpahtImgs;
	}

	
	public String getXpathChapterName() {
		return xpathChapterName;
	}

	public void setXpathChapterName(String xpathChapterName) {
		this.xpathChapterName = xpathChapterName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMangaId() {
		return mangaId;
	}

	public void setMangaId(String mangaId) {
		this.mangaId = mangaId;
	}

	public Link(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}

	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
