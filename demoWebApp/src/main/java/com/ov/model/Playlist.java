package com.ov.model;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection="playlist_table")
@Document(collection="playlist")
public class Playlist extends GeneralModel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1266525524600889357L;

	@Override
	public String toString() {
		return "Playlist [name=" + name + ", songIds="
				+ Arrays.toString(songIds) + "]";
	}

	private String name;
	private String[] songIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSongIds() {
		return songIds;
	}

	public void setSongIds(String[] songIds) {
		this.songIds = songIds;
	}
}
