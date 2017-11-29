package com.websystique.springmvc.model;

public class Link{

	private String name;
	
	//from web????
	private String webId;
	
	private String webName;
	
	// main link to get all Chapters
	private String mangaUrl;
	
	//xpath to get Chapters
	private ChapterSelectorSyntax chapterSelector;
	
	//xpath to get images
	private ImageSelectorSyntax imgSelector;

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
	
	public ChapterSelectorSyntax getChapterSelector() {
		return chapterSelector;
	}

	public void setChapterSelector(ChapterSelectorSyntax chapterSelector) {
		this.chapterSelector = chapterSelector;
	}

	public ImageSelectorSyntax getImgSelector() {
		return imgSelector;
	}

	public void setImgSelector(ImageSelectorSyntax imgSelector) {
		this.imgSelector = imgSelector;
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
	
	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	@Override
	public String toString() {
		return "Link [name=" + name + ", webId=" + webId + ", webName=" + webName + ", mangaUrl=" + mangaUrl
				+ ", chapterSelector=" + chapterSelector + ", imgSelector=" + imgSelector + "]";
	}

	
}
