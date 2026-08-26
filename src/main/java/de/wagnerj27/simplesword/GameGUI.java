package de.wagnerj27.simplesword;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameGUI extends Application{
	private Scene mainScene;

		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			
			BorderPane root = new BorderPane();
	         mainScene = new Scene(root,800,800);


	        // Set the main scene on the application window.
	        primaryStage.setScene(mainScene);

	        // Display the application window.
	        primaryStage.show();
		}
}
