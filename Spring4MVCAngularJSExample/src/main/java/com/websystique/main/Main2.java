package com.websystique.main;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.CharSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Main2 {

	public static void main(String[] args) throws IOException {
		double a = -1;
		System.out.println(a);
		
		// main
//		String url = "http://www.nettruyen.com/truyen-tranh/cuu-vi-ho-ly-phan-2";
//		Document document = Jsoup.connect(url).get();
//
//		Elements chapterList = document.select("div.col-xs-5.chapter > a");
//		for (Element chapter : chapterList) {
//			System.out.println("Chapter Name:" +  chapter.text());
//			System.out.println("Chapter URL:" +  chapter.attr("href"));
//			System.out.println("Number: " + chapter.text().replaceAll("[^0-9.]","") );
//		}
		
		
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
		
		
		
		
//		String url = "http://truyentranh.net/one-piece";
//		Document document = Jsoup.connect(url).get();
//
//		Elements chapterList = document.select("div.content.mCustomScrollbar p a");
//		System.out.println(chapterList);
//		for (Element chapter : chapterList) {
//			System.out.println("Chapter Name:" +  chapter.attr("title"));
//			System.out.println("Chapter URL:" +  chapter.attr("href"));
//			System.out.println("Number: " + chapter.attr("title").replaceAll("[^0-9.]","") );
//		}
		
		
//		String url = "http://truyentranh.net/nanatsu-no-taizai/Chap-244";
//		Document document = Jsoup.connect(url).get();
////		document.charset(Charset.forName("UTF-8"));
//
//
//		Elements images = document.select("div.each-page img");
//		
//		System.out.println(images);
//		for (Element chapter : images) {
//			System.out.println("imageURL : " + chapter.attr("src"));
//		}
		
	}

}
