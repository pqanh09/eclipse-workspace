package com.ov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ov.model.Playlist;
import com.ov.service.PlaylistService;
import com.ov.utils.ResponseGeneral;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
	
	@Autowired
	@Qualifier("playlistSrv")
	private PlaylistService playlistSrv;
	
	@RequestMapping(value= "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseGeneral getPlaylists(){
		ResponseGeneral response = playlistSrv.handleGetCollections();
		return response;
	}
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseGeneral getPlaylists(@PathVariable String id){
		ResponseGeneral response = playlistSrv.handleGetCollectionById(id);
		return response;
	}
	
	@RequestMapping(value= "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseGeneral addPlaylists(@RequestBody Playlist plst){
		ResponseGeneral response = playlistSrv.handleAddCollection(plst);
		return response;
	}
	
	@RequestMapping(value= "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseGeneral updatePlaylists(@RequestBody Playlist plst){
		
		ResponseGeneral response = playlistSrv.handleUpdateCollection(plst);
		return response;
	}
	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseGeneral deletePlaylists(@PathVariable("id") String id){
		
		ResponseGeneral response = playlistSrv.handleDeleteCollection(id);
		return response;
	}
	
//	@RequestMapping(value= "/playlists/getByIdSong/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseGeneral getByIdSong(@PathVariable("id") String id){
//		ResponseGeneral response = playlistSrv.handleGetPlaylistsBySongId(id);
//		return response;
//	}
	
}
