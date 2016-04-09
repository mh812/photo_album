package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class BackEnd implements Serializable {
	
	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = 7878048212514926262L;
	
	
	private ArrayList<User> users;
	
	public BackEnd() {
		users = new ArrayList<User>();

		User admin = new User("admin");
		users.add(admin);

		User omar = new User("omar");
		users.add(omar);
	}
	
	public void addUser(String username) {
		User user = new User(username);
		users.add(user);
	}
	
	public void deleteUser(String username) {
		Iterator<User> iter = users.iterator();
		
		while (iter.hasNext()) {
			User u = iter.next();
			
			if (u.getUsername().equals(username)) {
				users.remove(u);
				return;
			}
		}
	}
	
	public User getUser(String username) {
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<User> getUserList() {
		return this.users;
	}
	
}
