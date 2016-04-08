package model;

import java.io.File;
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
public class Photo {

	private File fileName;
	private String caption;
	private List<Tag> tags;
	private Calendar dateTaken;
	private Album album;
	
	public Photo(File fileName, String caption, Album album, List<Tag> tags) {
		this.fileName = fileName;
		this.caption = caption;
		this.tags = tags;
		this.album = album;
		
		// sets dateTaken of Photo object - cannot be modified
		this.dateTaken = Calendar.getInstance();
		dateTaken.setTime(new Date(fileName.lastModified()));		
	}
	
	public File getFileName() {
		return this.fileName;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public List<Tag> getTags() {
		return this.tags;
	}
	
	public Calendar getDate() {
		return this.dateTaken;
	}
	
	public String printDate() {
		Date toDate = this.dateTaken.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:ss.SSS");	
		String date = format.format(toDate);
		return date;
	}
	
	public Album getAlbum() {
		return this.album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public boolean containsTag(String type, String value) {
		for (Tag t : tags) {
			if (t.getType().compareTo(type) == 0) {
				if (t.getValue().compareTo(value) == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean addTag(String type, String value) {
		if (containsTag(type, value)) {
			return false;
		} else {
			Tag t = new Tag(type, value);
			tags.add(t);
			return true;
		}
	}
	
	public String toString() {
		return "\n(" + getFileName().toString() + " " + getCaption() + " " + getTags() + ")\n"; 
	}
	
	
	
}
