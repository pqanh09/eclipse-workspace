package com.websystique.springmvc.request;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.vo.MangaVO;

public class MangaRequestObject extends GenericRequestObject{
	private MangaVO maga;
	private List<String> ids = new ArrayList<>();
	
	public MangaVO getMaga() {
		return maga;
	}
	public void setMaga(MangaVO maga) {
		this.maga = maga;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
