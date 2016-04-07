package view;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.PhotoAlbum;
import model.User;

public class PhotoDetailsController {
	
	@FXML
	private Label filename;
	@FXML
	private TextField caption;
	
	// add tag type-value input too

	@FXML
	private Button uploadBtn;
	@FXML
	private Button addBtn;
	@FXML
	private Button cancelBtn;

	private File fileName;
	
	private Stage stage;
	private PhotoAlbum photoAlbum;
	private User user;
	private Album album;
	
	protected void backToAlbum(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/AlbumScreen.fxml"));
		loader.load();
		Parent p = loader.getRoot();
		
		AlbumController controller = loader.getController();
		controller.setPhotoAlbum(photoAlbum);
		controller.setUser(this.user);
		controller.setAlbum(this.album);
		controller.setPhotoList(this.album);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.setTitle("Album: " + album.getAlbumName());
		stage.show();
	}
	
	@FXML
	protected void add(ActionEvent event) throws IOException {
		System.out.println("In PhotoDetailsController: saveDetails");
		String cap = caption.getText();
		
		// will all photos have to have a caption?
		if (this.fileName == null || cap.compareTo("") == 0) {
			//System.out.println("\tERROR Must enter text for File Name and Caption");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("Invalid inputs: Please upload file and enter caption.");
			alert.setContentText("Please try again.");
			alert.showAndWait();
		} else {
			boolean status = album.addPhoto(this.fileName, cap, null);
			if (!status) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("Duplicate photo.");
				alert.setContentText("Please try again.");
				alert.showAndWait();	
			} else {
				backToAlbum(event);
			}
		} 
		
	}
	
	@FXML
	protected void cancel(ActionEvent event) throws IOException {
		System.out.println("In PhotoDetailsController: cancel");
		backToAlbum(event);
	}
	
	@FXML
	protected void upload(ActionEvent event) throws IOException {
		System.out.println("In PhotoDetailsController: choose");
		FileChooser fileChooser = new FileChooser();		
        FileChooser.ExtensionFilter filter = 
        		new FileChooser.ExtensionFilter("All Images", "*.*"); // choose only image files
        fileChooser.getExtensionFilters().add(filter); // add filter to file chooser
        File file = fileChooser.showOpenDialog(stage);
        this.fileName = file;
		filename.setText(fileName.toString());
	}
	
	/**
	 * Sets instance of PhotoAlbum.
	 * 
	 * @param photoAlbum
	 */
	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	/**
	 * Sets value of user.
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}	
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
