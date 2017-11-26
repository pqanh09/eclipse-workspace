package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.request.ObjectType;

public class MangaVO extends AbstractDelegationObjectIdVO<Manga>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2405270521295993745L;

	
	@Override
	protected Manga newInstance() {
		return new Manga();
	}

	@Override
	public String retrieveNGObjectType() {
		return ObjectType.Manga.toString();
	}

	public MangaVO(Manga manga){
		super(manga);
	}
	public static List<MangaVO> fromProfiles(List<Manga> mangas) {
        List<MangaVO> result = new ArrayList<MangaVO>(mangas.size());
        for(Manga manga: mangas){
            result.add(new MangaVO(manga));
        }
        return result;
    }
	
	public String getName() {
		return delegate.getName();
	}

	public void setName(String name) {
		delegate.setName(name);
	}

	public String getAuthor() {
		return delegate.getAuthor();
	}

	public void setAuthor(String author) {
		delegate.setAuthor(author);
	}

	public String getMainLink() {
		return delegate.getMainLink();
	}

	public void setMainLink(String mainLink) {
		delegate.setMainLink(mainLink);
	}

	public List<String> getLinks() {
		return delegate.getLinks();
	}

	public void setLinks(List<String> links) {
		delegate.setLinks(links);
	}
}
