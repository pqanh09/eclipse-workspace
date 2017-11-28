package com.websystique.springmvc.controller;

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

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.MangaRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.response.GenericResponseObject;
import com.websystique.springmvc.service.MangaService;

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
	

}