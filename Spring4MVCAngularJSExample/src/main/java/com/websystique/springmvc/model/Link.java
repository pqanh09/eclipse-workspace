package com.websystique.springmvc.model;

public class Link{

	private String name;
	
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

	public Link() {
		super();
	}

	
}
