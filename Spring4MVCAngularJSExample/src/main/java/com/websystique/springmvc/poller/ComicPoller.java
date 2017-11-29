package com.websystique.springmvc.poller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Chapter;
import com.websystique.springmvc.model.ChapterSelectorSyntax;
import com.websystique.springmvc.model.ImageSelectorSyntax;
import com.websystique.springmvc.model.Link;
import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.repositories.ChapterRepository;
import com.websystique.springmvc.repositories.MangaRepository;

@Service("comicPoller")
public class ComicPoller {
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(ComicPoller.class);
	
	@Autowired
	private MangaRepository mangaRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	public void pollManga(String mangaId){
		Manga manga = mangaRepository.findOne(new ObjectId(mangaId));
		if(manga == null){
			LOGGER.error("Not found any Manga to poll");
			return;
		}
		String mainLinkName = manga.getMainLinkName();
		List<Link> links = manga.getLinks();
		Link mainLink = null;
		for (Link link : links) {
			if(mainLinkName.equals(link.getName())){
				mainLink = link;
			}
		}
		//get data from main link, if false: get data from other links
		if(!pollLink(mainLink, manga)){
			for (Link link : links) {
				if(!mainLinkName.equals(link.getName())){
					if(pollLink(mainLink, manga)){
						break;
					}
				}
			}
		}
		LOGGER.info("Polling Manga successfully");
	}
	private Chapter getChapterFromElement(ChapterSelectorSyntax chapterSS, Element element){
		Chapter chapter = new Chapter();
		try{
			//parse index
			String indexAttKey = chapterSS.getIndexAttKey();
			String numStr = "";
			if(StringUtils.isNotBlank(indexAttKey)){
				numStr = element.attr(indexAttKey);
			} else {
				numStr = element.text().replaceAll("[^0-9.]","");
			}
			if(StringUtils.isNotBlank(numStr)){
				chapter.setOrdinalNumber(Double.parseDouble(numStr));
			}
			//parse  name
			String nameAttKey = chapterSS.getNameAttKey();
			if(StringUtils.isNotBlank(nameAttKey)){
				chapter.setName(element.attr(nameAttKey));
			} else {
				chapter.setName(element.text());
			}
			//parse url
			chapter.setUrl(element.attr(chapterSS.getUrlAttKey()));
		} catch (Exception e) {
			LOGGER.info("getChapterFromElement::Element: {}", element.outerHtml());
			LOGGER.error("getChapterFromElement::An error when parsing Element: ", e);
			return null;
		}
		return chapter;
	}
	//poll data from Site
	private boolean pollLink(Link link, Manga manga){
		
		try {
			double latestChapter = manga.getLatestChapterOrdinalNumber();
			String mangaUrl = link.getMangaUrl();
			Document chapterDoc = Jsoup.connect(mangaUrl).get();
			ChapterSelectorSyntax chapterSS = link.getChapterSelector();
			Elements chapterList = chapterDoc.select(chapterSS.getCssQuery());
			List<Chapter> chapters = new ArrayList<>();
			for (Element eChapter : chapterList) {
				Chapter chapter = getChapterFromElement(chapterSS, eChapter);
				if(chapter != null){
					chapter.setMangaId(manga.getInstanceid().toString());
					chapter.setLinkName(link.getName());
					chapters.add(chapter);
				}
			}
			if(!chapterSS.isIncreasing()){
				Collections.reverse(chapters);
			}
			Iterator<Chapter> iterator =  chapters.iterator();
			// find new chapter
			boolean newChapter = false;
			while(iterator.hasNext()){
				Chapter chapter = iterator.next();
				// get first -> end
				if(latestChapter < 0 ) {
					pollChapter(chapter, link.getImgSelector());
					newChapter = true;
				} else {
					//get latest
					if(latestChapter < chapter.getOrdinalNumber()){
						pollChapter(chapter, link.getImgSelector());
						newChapter = true;
					} else {
						continue;
					}
				}
				latestChapter = chapter.getOrdinalNumber();
			}
			if(latestChapter <= manga.getLatestChapterOrdinalNumber() && !newChapter) {
				LOGGER.warn("Not have new chapter for this Manga {} at Site {}", manga.getName(), link.getWebName() );
				return false;
			}
		} catch (IOException e) {
			LOGGER.info("Link: {}", link.toString());
			LOGGER.error("An error when polling Link: ", e);
			return false;
		}
		
		return true;
		
		
//		String url = "http://www.nettruyen.com/truyen-tranh/hiep-khach-giang-ho/chap-534/352808";
//		Document document = Jsoup.connect(url).get();
////		document.charset(Charset.forName("UTF-8"));
//
//
//		Elements images = document.select("div.page-chapter img");
//		
//		System.out.println(images);
//		for (Element chapter : images) {
//			System.out.println("Number: " +  chapter.attr("data-index"));
//			System.out.println("imageURL : " + chapter.attr("src"));
//		}
	}
	//get image list from Chapter
	private void pollChapter(Chapter chapter, ImageSelectorSyntax imageSelectorSyntax){
		List<String> results = new ArrayList<>();
		chapter.setImages(results);
		Element temp = null;
		try{
			String chapterUrl = chapter.getUrl();
			Document document = Jsoup.connect(chapterUrl).get();
			
			Elements eImages = document.select(imageSelectorSyntax.getCssQuery());
			for (Element eImage : eImages) {
				results.add(eImage.attr(imageSelectorSyntax.getUrlAttKey()));
			}
			chapterRepository.save(chapter);
		}catch (Exception e){
			LOGGER.info("pollChapter::Element: {}", (temp != null) ? temp.outerHtml() : "No outer html");
			LOGGER.error("pollChapter::An error when parsing Element: ", e);
		}
	}
}
