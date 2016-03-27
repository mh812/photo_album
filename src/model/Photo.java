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
public class Photo {

	private String fileName;
	private String caption;
	private List<Tag> tags;
	private Calendar dateTaken;
	
	public Photo(String fileName, String caption, Calendar dateTaken) {
		this.fileName = fileName;
		this.caption = caption;
		this.dateTaken = dateTaken;
		this.tags = new ArrayList<Tag>();;
	}
	
	public String getFileName() {
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
	
	public boolean containsTag(Tag tag) {
		if (tags.contains(tag)) {
			return true;
		}
		return false;
	}
	
	public boolean addTag(String type, String value) {
		Tag tag = new Tag(type, value);
		if (!this.containsTag(tag)) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTag(Tag tag) {
		if (this.containsTag(tag)) {
			this.getTags().remove(tag);
			return true;
		} 
		return false;
	}
	
	public String toString() {
		return "\n(" + getFileName() + " " + getCaption() + " " + getTags() + ")\n"; 
	}
	
	
	
}
