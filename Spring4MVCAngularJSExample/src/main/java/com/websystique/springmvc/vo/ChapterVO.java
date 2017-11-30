package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

public class ChapterVO extends GenericVO {

	private String fullName;
	
	private String mangaId;

	private double ordinalNumber = -1.0;

	private List<String> images = new ArrayList<>();

	private String url;

	private WebVO web;
	
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

	public WebVO getWeb() {
		return web;
	}

	public void setWeb(WebVO web) {
		this.web = web;
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "ChapterVO [fullName=" + fullName + ", mangaId=" + mangaId + ", ordinalNumber=" + ordinalNumber
				+ ", images=" + images + ", url=" + url + ", web=" + web +"]";
	}

	
}
