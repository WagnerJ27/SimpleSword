package de.wagnerj27.simplesword;

import javafx.application.Application;

import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
public class GameGUI extends Application{
	private Scene mainScene;
	private Room room;
	private RoomRenderer roomRenderer;
	private Canvas canvas;
	private Player player;
	private int tileSize;
	private int canvasWidth;
	private int canvasHeight;
	private int windowWidth;
	private int windowHeight;
	private int gameWidth;
	private int gameHeight;
	private MovementController movementController;
		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			windowWidth = 800;
			windowHeight = 800;
			gameWidth = 800;
			gameHeight = 650;
			tileSize = 80;
			
			BorderPane root = new BorderPane();
			room = new Room(8,7);
			player = new Player(3, 4);
			movementController = new MovementController();
			root.setStyle("-fx-background-color: black;");
			
			canvas = new Canvas(gameWidth, gameHeight);
			roomRenderer = new RoomRenderer(room, canvas,tileSize,player);
			roomRenderer.render();
			
			root.setCenter(canvas);
			
			
			
			
			mainScene = new Scene(root, windowWidth, windowHeight);
	         
			mainScene.setOnKeyPressed(event ->{
				if(event.getCode()==KeyCode.W) {
					movementController.moveForward(player, room);
					roomRenderer.render();
				}
				if(event.getCode()==KeyCode.A) {
					player.turnLeft();
					roomRenderer.render();
				}
				if(event.getCode()==KeyCode.D) {
					player.turnRight();
					roomRenderer.render();
				}
			});
			
	        // Set the main scene on the application window.
	        primaryStage.setScene(mainScene);

	        // Display the application window.
	        primaryStage.show();
		}
}
