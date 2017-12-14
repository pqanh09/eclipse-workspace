package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class MangaJob extends Job{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805009792183091986L;
	
	private List<String> mangas = new ArrayList<>();
	
	public List<String> getMangas() {
		return mangas;
	}

	public void setMangas(List<String> mangas) {
		this.mangas = mangas;
	}

	public MangaJob() {
		super();
	}

	@Override
	public String toString() {
		return "MangaJob [mangas=" + mangas + "]";
	}
	

}
