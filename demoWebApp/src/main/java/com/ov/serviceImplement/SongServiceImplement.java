package com.ov.serviceImplement;


import com.ov.model.Song;
import com.ov.repository.PlaylistRepository;
import com.ov.repository.SongRepository;
import com.ov.responseEntity.GeneralObject;
import com.ov.responseEntity.SongObject;
import com.ov.service.SongService;

//@Service
public class SongServiceImplement extends GeneralServiceImplement<Song> implements SongService{

	private SongRepository songRepository;
	private PlaylistRepository playListRepository;
	

	public SongRepository getSongRepository() {
		return songRepository;
	}


	public void setSongRepository(SongRepository songRepository) {
		this.songRepository = songRepository;
	}


	public PlaylistRepository getPlayListRepository() {
		return playListRepository;
	}


	public void setPlayListRepository(PlaylistRepository playListRepository) {
		this.playListRepository = playListRepository;
	}


	public SongServiceImplement(){
	}
	
	
	public SongServiceImplement(PlaylistRepository playListRepository, SongRepository songRepository) {
		super(songRepository);
		setPlayListRepository(playListRepository);
		setSongRepository(songRepository);
	}

	
	@Override
	public GeneralObject convertObject(Song song) {
		return new SongObject(song.getId(), song.getName(), song.getType(), song.getAuthor(), song.getSongPath());
	}
	

}
