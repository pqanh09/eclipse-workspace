package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Chapter.COLLECTION_NAME)
public class Chapter extends GenericModel<ObjectId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152250095558348755L;

	public static final String COLLECTION_NAME = "Chapter";

	@NotNull
	@Size(max = 250)
	@Indexed(unique = true)
	private String name;

	private String mangaId;
	
	private int ordinalNumber = 0;
	
	private List<String> images = new ArrayList<>();
	
	private String linkId;

	

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

	public int getOrdinalNumber() {
		return ordinalNumber;
	}

	public void setOrdinalNumber(int ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Chapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chapter(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}


}
