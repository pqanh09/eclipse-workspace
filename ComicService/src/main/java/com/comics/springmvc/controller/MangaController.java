package com.comics.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.request.MangaJobRequestObject;
import com.comics.springmvc.request.MangaRequestObject;
import com.comics.springmvc.request.ObjectType;
import com.comics.springmvc.request.RequestType;
import com.comics.springmvc.response.GenericResponseObject;
import com.comics.springmvc.service.MangaService;

@RestController
@RequestMapping("/api")
public class MangaController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(MangaController.class);
	
	@Autowired
	MangaService mangaService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Manga--------------------------------------------------------

	@RequestMapping(value = "/manga", method = RequestMethod.GET)
	public ResponseEntity<GenericResponseObject> listAllUsers() {
		GenericResponseObject responseObject = mangaService.findAll(new GenericRequestObject(RequestType.read, ObjectType.Manga, null));
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}


	// -------------------Create a Manga

	@RequestMapping(value = "/manga", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> createUser(@RequestBody MangaRequestObject requestObject) {
		//LOGGER.info(requestObject.toString());
		requestObject.setObjectType(ObjectType.Manga);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = mangaService.create(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Update a Manga
	// --------------------------------------------------------

	@RequestMapping(value = "/manga", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseObject> updateUser(@RequestBody MangaRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Manga);
		requestObject.setOperation(RequestType.update);
		GenericResponseObject responseObject = mangaService.update(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Delete Manga(s)
	// --------------------------------------------------------

	@RequestMapping(value = "/manga", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponseObject> deleteUser(@RequestBody MangaRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Manga);
		requestObject.setOperation(RequestType.delete);
		GenericResponseObject responseObject = mangaService.delete(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}
	
	// -------------------Create a Manga Job
	
	@RequestMapping(value = "/manga/job", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GenericResponseObject> createMangaJob(@RequestBody MangaJobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Manga);
		requestObject.setOperation(RequestType.create);
		GenericResponseObject responseObject = mangaService.createMangaJob(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}

	// ------------------- Update a Manga Job
	// --------------------------------------------------------

	@RequestMapping(value = "/manga/job", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponseObject> updateMangaJob(@RequestBody MangaJobRequestObject requestObject) {
		requestObject.setObjectType(ObjectType.Manga);
		requestObject.setOperation(RequestType.update);
		GenericResponseObject responseObject = mangaService.updateMangaJob(requestObject);
		return new ResponseEntity<GenericResponseObject>(responseObject, HttpStatus.OK);
	}


}