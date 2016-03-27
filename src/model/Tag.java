package model;

/**
 * 
 * @author Omar Khalil
 * @author Michelle Hwang
 *
 */
public class Tag {

	private String type;
	private String value;
	
	public Tag(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return "\n\t(" + this.getType() + ", " + this.getValue() + ")\n";
	}
	
}
