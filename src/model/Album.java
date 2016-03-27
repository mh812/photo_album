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
public class Album {

	private String albumName;
	private int numPhotos;
	private List<Photo> photos;
	
	public Album(String albumName) {
		this.albumName = albumName;
		this.numPhotos = 0;
		this.photos = new ArrayList<Photo>();
	}
	
	// create album out of search results
	public Album(String albumName, int numPhotos, List<Photo> photos) {
		this.albumName = albumName;
		this.numPhotos = numPhotos;
		this.photos = photos;
	}
	
	public String getAlbumName() {
		return this.albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public int getNumPhotos() {
		return this.numPhotos;
	}
	
	private List<Photo> getPhotos() {
		return this.photos;
	}
	
	public boolean containsPhoto(Photo photo) {
		return photos.contains(photo);
	}
	
	public boolean addPhoto(String fileName, String caption, Calendar dateTaken) {
		Photo photo = new Photo(fileName, caption, dateTaken);
		if (!this.containsPhoto(photo)) {
			photos.add(photo);			
			this.numPhotos++;
			return true;
		}
		return false;
	}
	
	public boolean deletePhoto(Photo photo) {
		if (this.containsPhoto(photo)) {
			photos.remove(photo);
			this.numPhotos--;
			return true;
		}
		return false;
	}
	
	public boolean editPhoto(Photo photo) {
		if (this.containsPhoto(photo)) {
			return true;
		}
		return false;
	}
		
	public List<Photo> searchAlbum(Calendar date1, Calendar date2) {
		List<Photo> results = new ArrayList<Photo>();
		for (Photo photo : this.getPhotos()) {
			// check if photo's dateTaken is between date parameters
			// add to results list if true
		}
		return results;
	}
	
	public List<Photo> searchAlbum(Tag tag) {
		List<Photo> results = new ArrayList<Photo>();
		for (Photo photo : this.getPhotos()) {
			if (photo.containsTag(tag)) {
				results.add(photo);
			}
		}
		return results;
	}
	
	public String toString() {
		return "\n" + this.getAlbumName() + ":\n" + this.getPhotos() + "\n";
	}
	
	
	
}
