package com.comics.springmvc.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comics.springmvc.request.ChapterRequestObject;
import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.request.ObjectType;
import com.comics.springmvc.request.RequestType;
import com.comics.springmvc.request.WebRequestObject;
import com.comics.springmvc.response.GenericResponseObject;
import com.comics.springmvc.service.ChapterService;
import com.comics.springmvc.service.WebService;

@RestController
@RequestMapping("/api")
public class ChapterController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(ChapterController.class);
	
	@Autowired
	ChapterService chapterService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Chapter--------------------------------------------------------

	@RequestMapping(value = "/chapter/manga/{mangaId}", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getChapterByManga(@PathVariable String mangaId) {
		ChapterRequestObject request = new ChapterRequestObject();
		request.setOperation(RequestType.read);
		request.setObjectType(ObjectType.Chapter);
		request.setIds(Arrays.asList(mangaId));
		GenericResponseObject responseObject = chapterService.findByManga(request);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/chapter/id/{chapterId}", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> getChapter(@PathVariable String chapterId) {
		ChapterRequestObject request = new ChapterRequestObject();
		request.setOperation(RequestType.read);
		request.setObjectType(ObjectType.Chapter);
		request.setIds(Arrays.asList(chapterId));
		GenericResponseObject responseObject = chapterService.findOne(request);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
}