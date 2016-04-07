package model;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BackEnd implements Serializable {

	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = 4697633446212598817L;

	private ArrayList<User> users;
	
	public static final String storeDir = "dat";
	public static final String storeFile = "users.dat";
	
	public static BackEnd readUsers() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(storeDir + File.separator + storeFile));
		BackEnd back = (BackEnd)ois.readObject();
		return back;
	}
	
	public static void writeUsers(BackEnd back) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(storeDir + File.separator + storeFile));
		oos.writeObject(back);
		//oos.close();
	}
	
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
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				users.remove(u);
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
