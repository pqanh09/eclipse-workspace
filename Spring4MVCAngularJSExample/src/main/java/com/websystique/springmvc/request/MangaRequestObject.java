package com.websystique.springmvc.request;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.Link;
import com.websystique.springmvc.vo.MangaVO;

public class MangaRequestObject extends ModelRequestObject<MangaVO>{
	private List<Link> links = new ArrayList<>();

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}
