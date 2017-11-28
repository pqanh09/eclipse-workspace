package com.websystique.springmvc.vo;

public class LinkVO {
	
	private String objectId;
	
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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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

	public String getXpathChapterName() {
		return xpathChapterName;
	}

	public void setXpathChapterName(String xpathChapterName) {
		this.xpathChapterName = xpathChapterName;
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

	public LinkVO() {
		super();
	}

	@Override
	public String toString() {
		return "LinkVO [objectId=" + objectId + ", name=" + name + ", mangaId=" + mangaId + ", webId=" + webId
				+ ", mangaUrl=" + mangaUrl + ", xpathChapterName=" + xpathChapterName + ", xpathChapters="
				+ xpathChapters + ", xpahtImgs=" + xpahtImgs + "]";
	}
	
	
}
