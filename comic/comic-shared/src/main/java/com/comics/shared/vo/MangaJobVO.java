package com.comics.shared.vo;

import java.util.ArrayList;
import java.util.List;

public class MangaJobVO extends JobVO {
	private List<String> mangas = new ArrayList<String>();

	public List<String> getMangas() {
		return mangas;
	}

	public void setMangas(List<String> mangas) {
		this.mangas = mangas;
	}

	public MangaJobVO() {
		super();
	}
	
}
