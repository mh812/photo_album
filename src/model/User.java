package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * @author Omar Khalil
 * @author Michelle Hwang
 *
 */
public class User {
	private String username;
	private List<Album> albums;
	
	public User(String username) {
		this.username = username;
		this.albums = new ArrayList<Album>();
	}
	
	public User() {
		this(null);
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public List<Album> getAlbums() {
		return this.albums;
	}
	
	public Album containsAlbum(String albumname) {
		for(Album album : albums) {
			if (album.getAlbumName().compareTo(albumname) == 0) {
				return album;
			}
		}
		return null;
	}
	
	public boolean containsAlbum(Album album) {
		return albums.contains(album);
	}
	
	public boolean addAlbum(String albumName) {
		Album album = new Album(albumName);	
		if (!this.containsAlbum(album)) {
			return true;
		}
		return false;
	}
	
	public boolean deleteAlbum(Album album) {
		if (this.containsAlbum(album)) {
			this.getAlbums().remove(album);		
			return true;
		}
		return false;
	}
	
	public boolean movePhoto(Photo photo, Album src, Album dest) {
		return false;
	}
	
	public List<Photo> searchAlbums(Calendar date1, Calendar date2) {
		List<Photo> results = new ArrayList<Photo>();
		for (Album album : this.getAlbums()) {
			results.addAll(album.searchAlbum(date1, date2));
		}
		return results;
	}
	
	public List<Photo> searchAlbums(Tag tag) {
		List<Photo> results = new ArrayList<Photo>();
		for (Album album : this.getAlbums()) {
			results.addAll(album.searchAlbum(tag));
		}
		return results;
	}
	
	public void printAlbums() {
		System.out.println(this.getAlbums());
	}
	
}
