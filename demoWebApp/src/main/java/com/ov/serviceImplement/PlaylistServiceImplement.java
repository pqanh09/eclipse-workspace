package com.ov.serviceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ov.model.Playlist;
import com.ov.model.Song;
import com.ov.repository.PlaylistRepository;
import com.ov.repository.PlaylistRepositoryCustom;
import com.ov.repository.SongRepository;
import com.ov.repository.SongRepositoryCustom;
import com.ov.responseEntity.GeneralObject;
import com.ov.responseEntity.PlaylistObject;
import com.ov.responseEntity.SongObject;
import com.ov.service.PlaylistService;
import com.ov.utils.ResponseGeneral;
import com.ov.utils.ResponseSongManager;
import com.ov.utils.ResponseStatus;

public class PlaylistServiceImplement extends GeneralServiceImplement<Playlist> implements PlaylistService{
	private PlaylistRepository playlistRepository;
	private SongRepository songRepository;
	public SongRepository getSongRepository() {
		return songRepository;
	}
	public void setSongRepository(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	public PlaylistRepository getPlaylistRepository() {
		return playlistRepository;
	}
	public void setPlaylistRepository(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	public PlaylistServiceImplement(){}
	public PlaylistServiceImplement(PlaylistRepository playlistRepository, SongRepository songRepository) {
		super(playlistRepository);
		setPlaylistRepository(playlistRepository);
		setSongRepository(songRepository);
		
	}
	public GeneralObject convertSongObj(Song song){
		SongObject songObj = new SongObject(song.getId(), song.getName(), song.getType(), song.getAuthor(), song.getSongPath());
		return songObj;
	}
	
	@Override
	public GeneralObject convertObject(Playlist playlist) {
		PlaylistObject playlistObj = new PlaylistObject(playlist.getId(), playlist.getName());
		List<GeneralObject> songObjs = new ArrayList<GeneralObject>();
		if(playlist.getSongIds() != null){
			for (int i = 0; i < playlist.getSongIds().length; i++) {
//				Song song = songDao.getCollection(playlist.getSongIds()[i]);
				Song song = songRepository.findOne(playlist.getSongIds()[i]);
				songObjs.add(convertSongObj(song));
		     }
		}
		playlistObj.setSongs(songObjs);
		return playlistObj;
	}
	
//	public ResponseGeneral handleGetPlaylistsBySongId(String id) {
//		response = ResponseSongManager.responseBuilder();
//		response.setVersion("v1");
//		try {
//			List<Playlist> playlists = playlistRepository.findByIdSong(id);
//			List<GeneralObject> playlistObjs = new ArrayList<GeneralObject>();
//			for (Playlist playlist : playlists) {
//				playlistObjs.add(convertObject(playlist));
//			}
//			response.setResponse(playlistObjs);
//			response.setStatus(ResponseStatus.SUCCESS.toString());
//			response.setStatusCode(HttpStatus.OK.value());
//			response.setTypeResponse(playlistObjs.getClass().getSimpleName());
//		} catch (Exception e) {
//			response.setStatus(ResponseStatus.FAIL.toString());
//			response.setResponse(e);
//			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			response.setTypeResponse(e.getClass().getSimpleName());
//		}
//		
//		return response;
//	}
	
}
