package com.ov.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection="song_table")
@Document(collection="song")
public class Song extends GeneralModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5306210400331708101L;
	@NotBlank
	public String name;
	private String type;
	private String author;
	private String songPath;
//	private String[] idPlaylists;
	public Song(){
		
	}
	public Song(String songName, String type, String author, String songPath) {
		setName(songName); 
		setType(type);
		setAuthor(author);
		setSongPath(songPath);
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
	public String getSongPath() {
		return songPath;
	}
	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}
	@Override
	public String toString() {
		return "Song [name=" + name + ", type=" + type + ", author=" + author
				+ ", songPath=" + songPath + "]";
	}
	
//	public String[] getIdPlaylists() {
//		return idPlaylists;
//	}
//	public void setIdPlaylists(String[] idPlaylists) {
//		this.idPlaylists = idPlaylists;
//	}
	
	
	
}
