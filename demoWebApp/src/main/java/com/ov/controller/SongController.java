package com.ov.controller;


import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ov.model.Song;
import com.ov.service.SongService;
import com.ov.utils.FileInfo;
import com.ov.utils.ResponseGeneral;

@RestController
@RequestMapping(value = "/songs")
public class SongController {
	
//	@Autowired
//	@Qualifier("songDao")
//	private GeneralDao<Song> songServ;
	@Autowired
	ServletContext context;
	
	@Autowired
	@Qualifier("songSrv")
	private SongService songSrv;
	
	private ResponseGeneral  response;
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SongController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseGeneral getSongs(){
		response = songSrv.handleGetCollections();
		logger.debug("response of list song", response.toString());
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseGeneral getSongById(@PathVariable("id") String id){
		response = songSrv.handleGetCollectionById(id);
		return response;
	}
	
//	@RequestMapping(value="", method= RequestMethod.POST)
//	@ResponseBody
//	public ResponseGeneral addSong(@RequestBody @Valid Song song, BindingResult result){
//		if (result.hasErrors()) {
//            return songSrv.handleBadRequest(result.toString());
//        }
//		System.out.println(song.toString());
//		ResponseGeneral  response = songSrv.handleAddCollection(song);
//		return response;
//	}

	@RequestMapping(value="", method= RequestMethod.PUT)
	@ResponseBody
	public ResponseGeneral updateSong(@RequestBody @Valid Song song, BindingResult result){
//	public ResponseGeneral addSong(@ModelAttribute("song") @Valid Song song, BindingResult result){
		if (result.hasErrors()) {
            return songSrv.handleBadRequest(result.toString());
        }
		System.out.println(song.toString());
		response = songSrv.handleUpdateCollection(song);
		return response;
	}
	
	@RequestMapping(value= "/{id}", method= RequestMethod.DELETE)
	@ResponseBody
	public ResponseGeneral deleteSong(@PathVariable("id") String id) {
		response = songSrv.handleDeleteCollection(id);
		return response;
	}
	
	@RequestMapping(value= "", headers=("content-type=multipart/*"), method= RequestMethod.POST)
	@ResponseBody
	public ResponseGeneral upload(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "author", required = false, defaultValue = "") String author,
			@RequestParam(value = "file" , required = false) MultipartFile inputFile) {
		Song song = new Song();
		song.setName(name);
		song.setType(type);
		song.setAuthor(author);	
		
		
	  FileInfo fileInfo = new FileInfo();
//	  HttpHeaders headers = new HttpHeaders();
	  if (inputFile != null && !inputFile.isEmpty() ) {
	   try {
		   if(!inputFile.getContentType().equalsIgnoreCase("audio/mp3")){
			   String originalFilename = inputFile.getOriginalFilename();
			    File destinationFile = new File(context.getRealPath("/WEB-INF/uploaded")+  File.separator + originalFilename);
			    inputFile.transferTo(destinationFile);
			    fileInfo.setFileName(destinationFile.getPath());
			    fileInfo.setFileSize(inputFile.getSize());
//			    headers.add("File Uploaded Successfully - ", originalFilename);
			    
				song.setSongPath(context.getContextPath()+ "/WEB-INF/uploaded" +  "/" + originalFilename);
//			    return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
		   }else{
			   return songSrv.handleBadRequest("Can't upload file! Because wrong type . Please upload mp3 type!");
		   }
	    
	   } catch (Exception e) {
		   System.out.println(e.toString());
		   return songSrv.handleBadRequest("Can't upload file!");
//		   return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
	   }
	   
	  }
	  response = songSrv.handleAddCollection(song);
	  return response;
	 }
	
	
//	@RequestMapping(value= "/songs/playlists/{id}", method= RequestMethod.GET)
//	@ResponseBody
//	public ResponseGeneral getSongsByPlaylistId(@PathVariable("id") String id) {
//		ResponseGeneral  response = songSrv.handleGetSongsByPlaylistId(id);
//		return response;
//	}
	
//	@RequestMapping(value= "/song/edit/{id}", method= RequestMethod.GET)
//	@ResponseBody
//	public String showInfoEditSong(@PathVariable("id") String id, ModelMap model){
//		Song song = songServ.getCollection(id);
//		model.addAttribute("title", "edit Song: " + song.name);
//		model.addAttribute("song", song);
//		model.addAttribute("songs", songServ.getCollections());
//		return VIEW_INDEX;
//	}
//	
//	@RequestMapping(value= "/{name}",method = RequestMethod.GET)
//	@ResponseBody
//	public String welcomeName(@PathVariable String name, ModelMap model){
//		model.addAttribute("message", "Welcome" + name);
//		model.addAttribute("counter", counter++);
//		logger.debug("[welcome] counter: {}", counter);
//		
//		return VIEW_INDEX;
//	}
}
