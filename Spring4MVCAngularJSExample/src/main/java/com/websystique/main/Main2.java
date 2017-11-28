package com.websystique.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Main2 {

	public static void main(String[] args) throws IOException {
		String url = "http://www.nettruyen.com/truyen-tranh/hiep-khach-giang-ho/chap-534/352808";
		Document document = Jsoup.connect(url).get();

//		String question = document.select("#question .post-text").text();
//		System.out.println("Question: " + question);

		Elements answerers = document.select(".page-chapter");
		for (Element answerer : answerers) {
			Node img = answerer.childNode(0);
			System.out.println("Picture: " + img.attr("src"));
		}
	}

}
