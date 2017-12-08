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
import com.websystique.springmvc.model.JobHistoryDetail;
import com.websystique.springmvc.model.Link;
import com.websystique.springmvc.model.Manga;
import com.websystique.springmvc.repositories.ChapterRepository;
import com.websystique.springmvc.repositories.MangaRepository;

import ch.qos.logback.classic.Level;

@Service("comicPoller")
public class ComicPoller {
	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(ComicPoller.class);
	
	@Autowired
	private MangaRepository mangaRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	public JobHistoryDetail pollManga(String mangaId){
		JobHistoryDetail historyDetail = new JobHistoryDetail();
		historyDetail.setElementId(mangaId);
		historyDetail.setSuccess(true);
		Manga manga = mangaRepository.findOne(new ObjectId(mangaId));
		if(manga == null){
			LOGGER.error("Not found any Manga to poll");
			historyDetail.setSuccess(false);
			historyDetail.addLog(Level.ERROR, "Not found any Manga to poll");
			return historyDetail;
		}
		historyDetail.setName(manga.getName());
		String mainLinkName = manga.getMainLinkName();
		List<Link> links = manga.getLinks();
		Link mainLink = null;
		for (Link link : links) {
			if(mainLinkName.equals(link.getName())){
				mainLink = link;
			}
		}
		//poll main link and others
		//get data from main link, if false: get data from other links 
		if(mainLink != null){
			if(!pollLink(historyDetail, mainLink, manga)){
				for (Link link : links) {
					if(!mainLinkName.equals(link.getName())){
						if(pollLink(historyDetail, link, manga)){
							break;
						}
					}
				}
			}
		} 
		// main link null, poll others
		else {
			for (Link link : links) {
				if(pollLink(historyDetail, link, manga)){
					break;
				}
			}
		}
		LOGGER.info("Polling Manga successfully");
		historyDetail.addLog(Level.INFO, "Polling Manga successfully");
		return historyDetail;
	}
	private Chapter getChapterFromElement(JobHistoryDetail historyDetail, ChapterSelectorSyntax chapterSS, Element element){
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
			historyDetail.addLog(Level.INFO, String.format("Element: %s", element.outerHtml()));
			LOGGER.error("getChapterFromElement::An error when parsing Element: ", e);
			historyDetail.addLog(Level.ERROR, String.format("An error when parsing Element: %s", e.getMessage()));
			historyDetail.setSuccess(false);
			return null;
		}
		return chapter;
	}
	//poll data from Site
	private boolean pollLink(JobHistoryDetail historyDetail, Link link, Manga manga){
		LOGGER.info("Try to get Chapters Manga {} from Site {}",manga.getName(), link.getWebName());
		historyDetail.addLog(Level.INFO, String.format("Try to get Chapters of Manga %s from Site %s", manga.getName(), link.getWebName()));
		try {
			double latestChapterOrdinalNumber = manga.getLatestChapterOrdinalNumber();
			String mangaUrl = link.getMangaUrl();
			Document chapterDoc = Jsoup.connect(mangaUrl).get();
			ChapterSelectorSyntax chapterSS = link.getChapterSelector();
			Elements chapterList = chapterDoc.select(chapterSS.getCssQuery());
			List<Chapter> chapters = new ArrayList<>();
			for (Element eChapter : chapterList) {
				Chapter chapter = getChapterFromElement(historyDetail, chapterSS, eChapter);
				if(chapter != null){
					chapter.setMangaId(manga.getInstanceid().toString());
					chapter.setLinkName(link.getName());
					chapter.setWebId(link.getWebId());
					chapter.setFullName(manga.getName() + " - " + chapter.getName());
					chapters.add(chapter);
				}
			}
			if(!chapterSS.isIncreasing()){
				Collections.reverse(chapters);
			}
			Iterator<Chapter> iterator =  chapters.iterator();
			// find new chapter
			boolean newChapter = false;
			Chapter latestChapter = null;
			while(iterator.hasNext()){
				latestChapter = iterator.next();
				// get first -> end
				if(latestChapterOrdinalNumber < 0 ) {
					pollChapter(historyDetail, latestChapter, link.getImgSelector());
					newChapter = true;
				} else {
					//get latest
					if(latestChapterOrdinalNumber < latestChapter.getOrdinalNumber()){
						pollChapter(historyDetail, latestChapter, link.getImgSelector());
						newChapter = true;
					} else {
						continue;
					}
				}
				latestChapterOrdinalNumber = latestChapter.getOrdinalNumber();
			}
			if(latestChapterOrdinalNumber <= manga.getLatestChapterOrdinalNumber() && !newChapter) {
				LOGGER.warn("Not have new chapter for this Manga {} at Site {}", manga.getName(), link.getWebName());
				historyDetail.addLog(Level.WARN, String.format("Not have new chapter for this Manga %s at Site %s", manga.getName(), link.getWebName()));
//				historyDetail.setSuccess(true);
				return false;
			}
			if(latestChapter != null) {
				manga.setLatestChapterOrdinalNumber(latestChapter.getOrdinalNumber());
				manga.setLatestChapterName(latestChapter.getName());
				manga.setLatestChapterId(latestChapter.getInstanceid().toString());
				mangaRepository.safeSave(manga);
			}
		} catch (IOException e) {
			LOGGER.info("Link: {}", link.toString());
			historyDetail.addLog(Level.INFO, String.format("Link: %s", link.toString()));
			LOGGER.error("An error when polling Link: ", e);
			historyDetail.addLog(Level.ERROR, String.format("An error when polling Link: %s", e.getMessage()));
			historyDetail.setSuccess(false);
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
	private void pollChapter(JobHistoryDetail historyDetail, Chapter chapter, ImageSelectorSyntax imageSelectorSyntax){
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
			historyDetail.addLog(Level.INFO, String.format("Get Chapter %.1f successfully", chapter.getOrdinalNumber()));
			LOGGER.info(String.format("Get Chapter %.1f successfully", chapter.getOrdinalNumber()));
		}catch (Exception e){
			LOGGER.info("pollChapter::Element: {}", (temp != null) ? temp.outerHtml() : "No outer html");
			historyDetail.addLog(Level.INFO, String.format("Element: %s", (temp != null) ? temp.outerHtml() : "No outer html"));
			LOGGER.error("pollChapter::An error when parsing Element: ", e);
			historyDetail.addLog(Level.ERROR, String.format("An error when parsing Element: %s", e.getMessage()));
			historyDetail.setSuccess(false);
		}
	}
}
