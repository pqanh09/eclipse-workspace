package com.ov.responseEntity;

public class SongObject extends GeneralObject{
	private String name;
	private String type;
	private String author;
	private String path;
	
	public SongObject(String id, String name, String type, String author, String path){
			super(id);
			setName(name);
			setType(type);
			setAuthor(author);
			setPath(path);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
}
