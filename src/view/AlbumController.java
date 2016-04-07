package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Album;
import model.BackEnd;
import model.Photo;
import model.PhotoAlbum;
import model.User;

public class AlbumController {

	@FXML
	private Label caption;
	
	@FXML
	private Button addBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button editBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private Button prevBtn;
	@FXML
	private Button exitBtn;
	
	@FXML
	TilePane gallery;
	
	@FXML
	Pane preview;
	
	@FXML
	private Label albumName;
	@FXML
	private Label numPhotos;
	@FXML
	private Label oldestPhotoDate;
	@FXML
	private Label firstDate;
	@FXML
	private Label lastDate;
		
	private PhotoAlbum photoAlbum;
	private User user;
	private Album album;
	
	private Stage stage;
	
	@FXML
	private void initialize() {

	}
	
	private void showDetails(Photo photo) {
		if (photo != null) {
			showPhoto(photo);
			caption.setText(photo.getCaption());	
			
			ImageView imageView = new ImageView();
			Image image = new Image(photo.getFileName().toURI().toString(), 245, 174, true, true);
			imageView.setImage(image);
			imageView.setPreserveRatio(true);
			preview.getChildren().add(imageView);
			
		} else {
			caption.setText("");
		}
	}

	private void showPhoto(Photo photo) {
		// display image
	}
	@FXML
	protected void exit(ActionEvent event) throws IOException {
		System.out.println("In AlbumController: exit");
		
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/UserScreen.fxml"));
		loader.load();
		Parent p = loader.getRoot();
		
		UserController controller = loader.getController();
		controller.setPhotoAlbum(this.photoAlbum);
		controller.setUser(this.user);
		controller.setAlbumList(this.user);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(p));
		stage.show();
	}
	
	@FXML
	protected void delete(ActionEvent event) throws IOException {
		/*System.out.println("In AlbumController: delete");
		
		int item = photoList.getSelectionModel().getSelectedIndex();
		if (item >= 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Click OK to delete this photo.");
			Optional<ButtonType> click = alert.showAndWait();
			if ((click.isPresent()) && (click.get() == ButtonType.OK)) {
				//user.deleteAlbum(user.getAlbums().get(item));
				//photoList.getItems().remove(item);
				//photoList.getSelectionModel().select(item);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("No item selected");
			alert.setContentText("Please select a photo from the list to be deleted");
			alert.showAndWait();
		} */
	} 
	
	@FXML
	protected void add(ActionEvent event) throws IOException {
		System.out.println("In AlbumController: add");
		
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/PhotoDetailsScreen.fxml"));
		loader.load();
		Parent p = loader.getRoot();		
		
		PhotoDetailsController controller = loader.getController();
		controller.setPhotoAlbum(photoAlbum);
		controller.setUser(user);
		controller.setAlbum(album);

		Stage stage = new Stage();
		controller.setStage(stage);
		stage.setScene(new Scene(p));
		stage.setTitle("Add New Photo");
		stage.show();
		
	}
	
	@FXML
	protected void edit(ActionEvent event) throws IOException {
		System.out.println("In AlbumController: edit");
	}
	
	@FXML
	protected Photo next() {
		System.out.println("In AlbumController: next");
		return null;
	}
	
	@FXML
	protected Photo prev() {
		System.out.println("In AlbumController: prev");
		return null;
	}
	
	/**
	 * Sets album list on listview for the user to see.
	 * 
	 * @param user
	 */
/*	public void setPhotoList(Album album) {
		System.out.println("In AlbumController: setPhotoList()");
		List<Photo> photos = album.getPhotos();
		ObservableList<Photo> list = FXCollections.observableArrayList();
		for (Photo p : photos) {
			//System.out.println(a);
			list.add(p);
		}
		photoList.setItems(list);
	} */
	
	@FXML
	public void setPhotoList(Album album) {
		List<Photo> photos = this.album.getPhotos();
		for (Photo p : photos) {
			File f = p.getFileName();
			Image image = new Image(f.toURI().toString(), 100, 0, true, true);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(100);
			
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mouseEvent) {
					if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
						if(mouseEvent.getClickCount() == 1){

							System.out.println("Photo clicked: " + p.getFileName().toString());
							showDetails(p);
							/*
							BorderPane borderPane = new BorderPane();
							ImageView imageView = new ImageView();
							Image image = new Image(p.getFileName().toURI().toString());
							imageView.setImage(image);
							imageView.setPreserveRatio(true);
							borderPane.setCenter(imageView);
							Stage newStage = new Stage();
							Scene scene = new Scene(borderPane,Color.BLACK);
							newStage.setScene(scene);
							newStage.show();
							*/
						}
					}
				}
			});				

			gallery.getChildren().add(imageView);
		}
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
