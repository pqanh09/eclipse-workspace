package com.websystique.springmvc.model;

import java.util.ArrayList;
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
	public static final String ATTR_NAME = "name";
	public static final String ATTR_FULLNAME = "fullName";
	public static final String ATTR_MANGA_ID = "mangaId";
	public static final String ATTR_ORDINAL_NUMBER = "ordinalNumber";
	public static final String ATTR_IMAGES = "images";
	public static final String ATTR_URL = "url";
	public static final String ATTR_LINK_NAME = "linkName";
	public static final String ATTR_WEB_ID = "webId";


	private String name;

	@NotNull
	@Size(max = 250)
	@Indexed(unique = true)
	private String fullName;
	
	private String mangaId;
	
	private double ordinalNumber = -1.0;
	
	private List<String> images = new ArrayList<>();
	
	private String url;
	
	private String linkName;
	
	private String webId;

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

	public double getOrdinalNumber() {
		return ordinalNumber;
	}

	public void setOrdinalNumber(double ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public Chapter() {
		super();
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getWebId() {
		return webId;
	}

	public void setWebId(String webId) {
		this.webId = webId;
	}

	@Override
	public String toString() {
		return "Chapter [name=" + name + ", fullName=" + fullName + ", mangaId=" + mangaId + ", ordinalNumber="
				+ ordinalNumber + ", images=" + images + ", url=" + url + ", linkName=" + linkName + ", webId=" + webId
				+ "]";
	}

	
}
