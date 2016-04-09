package model;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Omar Khalil
 * @author Michelle Hwang
 *
 */
public class Album implements Serializable {

	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = 3187980270116508175L;
	
	private String albumName;
	private int numPhotos;
	private List<Photo> photos;
	private Calendar oldest;
	private Calendar newest;
	
	public Album(String albumName) {
		this.albumName = albumName;
		this.numPhotos = 0;
		this.photos = new ArrayList<Photo>();
		this.oldest = null;
		this.newest = null;
	}
	
	public String getAlbumName() {
		return this.albumName;
	}
	
	public void setAlbumName(String albumName) {
		//System.out.println("In Album: setAlbumName");
		this.albumName = albumName;
	}
	
	public int getNumPhotos() {
		return this.numPhotos;
	}
	
	public void setNumPhotos(int numPhotos) {
		this.numPhotos = numPhotos;
	}
	
	public List<Photo> getPhotos() {
		return this.photos;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	/**
	 * Checks if a photo file already exists in an album.
	 * 
	 * @param fileName Path of the photo
	 * @return True if the album contains the photo, false otherwise
	 */
	public boolean containsPhoto(File fileName) {
		for(Photo p : photos) {
			if (fileName.equals(p.getFileName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param caption
	 * @return
	 */
	public boolean addPhoto(File fileName, String caption, List<Tag> tags) {
		//System.out.println("In Album: addPhoto");
		if (!this.containsPhoto(fileName)) {
			Photo photo = new Photo(fileName, caption, this, tags);
			photos.add(photo);			
			this.numPhotos++;
			
			findOldest();
			findNewest();
			
			return true;
		}
		return false;
	}
	
	/**
	 * Removes a photo from the photo album.
	 * 
	 * @param photo Photo to be deleted
	 */
	public void deletePhoto(Photo photo) {
		//System.out.println("In Album: deletePhoto");
		photos.remove(photo);
		this.numPhotos--;
		
		findOldest();
		findNewest();
	}
	
	/**
	 * Returns the date of the oldest photo as a string.
	 * 
	 * @return oldest
	 */
	public String getOldest() {
		findOldest();
		if (oldest == null) {
			return "";
		}
		Date date = this.oldest.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:ss.SSS");	
		return format.format(date);
	}
	
	/**
	 * Returns the date of the newest photo as a string.
	 * 
	 * @return newest
	 */
	public String getNewest() {
		findNewest();
		if (newest == null) {
			return "";
		}
		Date date = this.newest.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:ss.SSS");	
		return format.format(date);
	}
	
	/**
	 * Identifies the oldest photo in the album.
	 */
	public void findOldest() {
		//System.out.println("In Album: findOldest");
		Calendar oldest;

		if (numPhotos == 0) {
			return;
		}
		
		//System.out.println(photos.get(0).printDate());
		oldest = photos.get(0).getDate();		
		for (Photo p : photos) {
			//System.out.println(oldest.compareTo(p.getDate()));
			if (oldest.compareTo(p.getDate()) > 0) {
				oldest = p.getDate();
				//System.out.println(p.printDate());
			}
		}
		this.oldest = oldest;
	}
	
	/**
	 * Identifies the newest photo in the album.
	 */
	public void findNewest() {
		Calendar newest;

		if (numPhotos == 0) {
			return;
		}
		
		//System.out.println(photos.get(0).printDate());
		newest = photos.get(0).getDate();		
		for (Photo p : photos) {
			//System.out.println(oldest.compareTo(p.getDate()));
			if (newest.compareTo(p.getDate()) < 0) {
				newest = p.getDate();
				//System.out.println(p.printDate());
			}
		}
		this.newest = newest;
	}
	
	public String toString() {
		return "\n" + this.getAlbumName() + ":\n" + this.getPhotos() + "\n";
	}
	
	
	
}
