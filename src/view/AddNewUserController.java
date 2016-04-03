package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BackEnd;
import model.PhotoAlbum;
import model.User;

/**
 * The AddNewUserController class adds a new user and goes back to the admin screen.
 * 
 * @author Omar Khalil
 * @author Michelle Hwang
 */
public class AddNewUserController {

	@FXML
	private TextField username;
	@FXML 
	private Button addBtn;
	@FXML
	private Button cancelBtn;
	
	private User user;
	
	private PhotoAlbum photoAlbum;
	
	/**
	 * Adds a new user.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void add(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
		loader.load();
		Parent p = loader.getRoot();
		//System.out.println(username.getText());
		
		AdminController controller = loader.getController();
		photoAlbum.getBackend().addUser(username.getText());
		controller.setPhotoAlbum(this.photoAlbum);
		controller.setUserList(this.user);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
	
	/**
	 * Cancels the action.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	protected void cancel(ActionEvent event) throws IOException  {
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AdminScreen.fxml"));
		loader.load();
		Parent p = loader.getRoot();
		
		AdminController controller = loader.getController();
		controller.setPhotoAlbum(this.photoAlbum);
		controller.setUserList(this.user);
		
		try {
			//this.photoAlbum.getBackend();
			BackEnd.writeUsers(this.photoAlbum.getBackend());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
	
	/**
	 * Sets instance of PhotoAlbum.
	 * 
	 * @param photoAlbum
	 */
	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}
	
}
