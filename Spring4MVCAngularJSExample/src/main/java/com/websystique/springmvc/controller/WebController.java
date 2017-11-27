package com.websystique.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.MangaService;
import com.websystique.springmvc.service.UserService;

@RestController
@RequestMapping("/api")
public class WebController {

	static private Logger LOGGER = (Logger) LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	MangaService mangaService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Web--------------------------------------------------------

//	@RequestMapping(value = "/web", method = RequestMethod.GET)
//	public ResponseEntity<List<User>> listAllUsers() {
//		
//		List<User> users = userService.findAllLinks();
//		if (users.isEmpty()) {
////			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You
////																			// many
////																			// decide
////																			// to
////																			// return
////																			// HttpStatus.NOT_FOUND
//			userService.initUsers();
//			users = userService.findAllLinks();
//		}
//		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//	}
//
//	// -------------------Retrieve Single
//	// User--------------------------------------------------------
//
//	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> getUser(@PathVariable("username") String username) {
//		LOGGER.info("Fetching User with Username " + username);
//		User user = userService.findByName(username);
//		if (user == null) {
//			LOGGER.error("User with Username " + username + " not found");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//
//	// -------------------Create a
//	// User--------------------------------------------------------
//
//	@RequestMapping(value = "/user/", method = RequestMethod.POST)
//	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//		LOGGER.info("Creating User " + user.getUsername());
//
//		if (userService.isUserExist(user)) {
//			LOGGER.error("A User with name " + user.getUsername() + " already exist");
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
//
//		userService.saveLink(user);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUsername()).toUri());
//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	}
//
//	// ------------------- Update a User
//	// --------------------------------------------------------
//
//	@RequestMapping(value = "/user", method = RequestMethod.PUT)
//	public ResponseEntity<User> updateUser(@RequestBody User user) {
//		String userName = user.getUsername();
//		LOGGER.info("Updating User: " + userName);
//
//		User currentUser = userService.findByName(userName);
//
//		if (currentUser == null) {
//			LOGGER.error("User with Username " + userName + " not found");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//		currentUser.setUsername(user.getUsername());
//		currentUser.setAddress(user.getAddress());
//		currentUser.setEmail(user.getEmail());
//
//		userService.updateLink(currentUser);
//		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//	}
//
//	// ------------------- Delete a User
//	// --------------------------------------------------------
//
//	@RequestMapping(value = "/user/{userName}", method = RequestMethod.DELETE)
//	public ResponseEntity<User> deleteUser(@PathVariable("userName") String userName) {
//		LOGGER.info("Fetching & Deleting User with UserName " + userName);
//
//		User user = userService.findByName(userName);
//		if (user == null) {
//			LOGGER.error("Unable to delete. User with UserName " + userName + " not found");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//		userService.deleteLink(user);
//		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//	}
//
//	// ------------------- Delete All Users
//	// --------------------------------------------------------
//
//	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//	public ResponseEntity<User> deleteAllUsers() {
//		System.out.println("Deleting All Users");
//
//		userService.deleteAllUsers();
//		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//	}

}