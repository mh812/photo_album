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
	
	public boolean containsAlbum(String albumName) {
		System.out.println("In User: containsAlbum");

		for(Album album : albums) {
			if (album.getAlbumName().compareTo(albumName) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a new album to a user's library. Will refuse to add an album with a 
	 * duplicate album name.
	 * 
	 * @param albumName The name of the album to be created
	 * @return True if the album was added, false otherwise.
	 */
	public boolean addAlbum(String albumName) {
		System.out.println("In User: addAlbum");
		
		if (containsAlbum(albumName)) {
			return false;
		}
		
		System.out.println("\t" + albumName + ": Not a duplicate, Will add");
		albums.add(new Album(albumName));
		return true;

	}
	
	public void editAlbum(String albumName, Album album) {
		System.out.println("In User: editAlbum");
		album.setAlbumName(albumName);
	}
	
	/**
	 * Removes an album from the user's library.
	 * 
	 * @param album The album object to be removed
	 */
	public void deleteAlbum(Album album) {
		System.out.println("In User: deleteAlbum");
		this.getAlbums().remove(album);
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
