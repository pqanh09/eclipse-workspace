package com.ov.responseEntity;

import java.util.List;

public class PlaylistObject extends GeneralObject{
	private String name;
	private List<GeneralObject> songs;
	
	public PlaylistObject(String id, String name){
		super(id);
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GeneralObject> getSongs() {
		return songs;
	}

	public void setSongs(List<GeneralObject> songObjs) {
		this.songs = songObjs;
	}
	
}
