
package de.wagnerj27.simplesword;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameGUI extends Application {

    private Scene mainScene;

    private DungeonLevel level;
    private RoomRenderer roomRenderer;
    private Canvas canvas;
    private Player player;

    private int gameLevel =1;
    
    private int tileSize;

    private int canvasWidth;
    private int canvasHeight;

    private int windowWidth;
    private int windowHeight;

    private int gameWidth;
    private int gameHeight;

    private Scene combatScene;
    private Combat combat;
    private Enemy enemy;

    private Label playerStatus;
    

    private static final int COMBAT_CHANCE = 10;

    private MovementController movementController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Set the window dimensions.
        windowWidth = 1200;
        windowHeight = 1000;

        // Set the game area dimensions.
        gameWidth = 800;
        gameHeight = 650;

        // Set the tile size.
        tileSize = 80;

        // Create the player.
        player = new Player(1, 1);

        // Create level 1.
        level = new DungeonLevel(21, 49,gameLevel);

        // Create the movement controller.
        movementController = new MovementController();

        // Create the main scene.
        createMainScene();

        // Set the main scene on the application window.
        primaryStage.setScene(mainScene);

        // Display the application window.
        primaryStage.show();
    }

    // Create and configure the main exploration scene.
    private void createMainScene() {

        // Create the main layout.
        BorderPane root = new BorderPane();

        // Set the background color.
        root.setStyle("-fx-background-color: black;");

        // Create the game canvas.
        canvas = new Canvas(gameWidth, gameHeight);

        // Create the dungeon renderer.
        roomRenderer = new RoomRenderer(
                level,
                canvas,
                tileSize,
                player
        );

        // Render the current level.
        roomRenderer.render2D();

        // Create the player status label.
        playerStatus = new Label();

        // Update the player status.
        updatePlayerStatus();

        // Set the player status text color.
        playerStatus.setTextFill(Color.WHITE);

        // Center the player status text.
        playerStatus.setAlignment(Pos.CENTER);

        // Increase the font size.
        playerStatus.setStyle("-fx-font-size: 24px;");

        // Place the canvas in the center.
        root.setCenter(canvas);

        // Place the player status at the bottom.
        root.setBottom(playerStatus);

        // Center the player status.
        root.setAlignment(playerStatus, Pos.CENTER);

        // Create the main scene.
        mainScene = new Scene(
                root,
                windowWidth,
                windowHeight
        );

        // Configure the keyboard controls.
        setupKeyboardControls();
    }

    // Configure the keyboard controls for the main scene.
    private void setupKeyboardControls() {

        // Handle keyboard input.
        mainScene.setOnKeyPressed(event -> {

            // Move the player forward when W is pressed.
            if (event.getCode() == KeyCode.W) {

                // Try to move the player.
                boolean moved =
                        movementController.moveForward(player, level);
                

                // Update the level rendering.
                roomRenderer.render2D();

                // Update the player status.
                updatePlayerStatus();

                //Check if player moved on stairs to next level
                if(moved) {
                	if(level.isStairs(player.getPosition().getX(), player.getPosition().getY())) {
                		System.out.println("Stairs reached");

                        gameLevel++;

                        System.out.println("Entered level " + gameLevel);

                        loadLevel();
                		
                	}
                }
                
                // Start combat if movement succeeded and the random chance occurs.
//                if (moved && shouldStartCombat()) {
//
//                    // Start combat.
//                    combat(mainScene.getWindow() instanceof Stage
//                            ? (Stage) mainScene.getWindow()
//                            : null, player);
//                }
            }

            // Turn the player left when A is pressed.
            if (event.getCode() == KeyCode.A) {

                // Turn the player left.
                player.turnLeft();

                // Update the level rendering.
                roomRenderer.render2D();
            }

            // Turn the player right when D is pressed.
            if (event.getCode() == KeyCode.D) {

                // Turn the player right.
                player.turnRight();

                // Update the level rendering.
                roomRenderer.render2D();
            }
        });
    }
    
    private void loadLevel() {
    	if(gameLevel==1) {
    		level = new DungeonLevel(21, 49, gameLevel);
    	}else if(gameLevel ==2) {
        level = new DungeonLevel(21, 49, gameLevel);
    	}
        roomRenderer = new RoomRenderer(
                level,
                canvas,
                tileSize,
                player
        );

        roomRenderer.render2D();
    }

    // Update the player status label with the current values.
    private void updatePlayerStatus() {

        playerStatus.setText(
                "Level: " + player.getLevel()
                + " | Player HP: "
                + player.getCurrentHP()
                + "/" + player.getMaxHP()
        );
    }

    // Create and display the combat scene.
    public void combat(Stage stage, Player player) {

        // Create the combat layout.
        BorderPane combatRoot = new BorderPane();

        // Set the combat background to black.
        combatRoot.setStyle("-fx-background-color: black;");

        // Create a new enemy.
        Enemy enemy = new Enemy();

        // Create the combat controller.
        Combat combat = new Combat(player, enemy);

        // Create the enemy information.
        VBox enemyBox = createEnemyStatus(enemy);

        // Place the enemy information at the top.
        combatRoot.setTop(enemyBox);

        // Create the player information.
        VBox playerBox = createPlayerCombatStatus(player);

        // Place the player information at the bottom.
        combatRoot.setBottom(playerBox);

        // Create the attack button.
        Button attackButton = createAttackButton();

        // Place the attack button in the center.
        combatRoot.setCenter(attackButton);

        // Create the combat scene.
        combatScene = new Scene(
                combatRoot,
                windowWidth,
                windowHeight
        );

        setupAttackButton(
                attackButton,
                combat,
                enemy,
                player,
                stage,
                playerBox,
                enemyBox
        );

        // Display the combat scene.
        stage.setScene(combatScene);
    }

    // Create the enemy status display.
    private VBox createEnemyStatus(Enemy enemy) {

        // Create the enemy name label.
        Label enemyName = new Label(enemy.getName());

        // Create the enemy HP label.
        Label enemyStatus = new Label(
                enemy.getCurrentHP()
                + "/"
                + enemy.getMaxHP()
                + " HP"
        );

        // Style the enemy labels.
        enemyName.setTextFill(Color.WHITE);
        enemyStatus.setTextFill(Color.WHITE);

        // Set readable font sizes.
        enemyName.setStyle(
                "-fx-font-size: 26px; -fx-font-weight: bold;"
        );

        enemyStatus.setStyle(
                "-fx-font-size: 20px;"
        );

        // Put the enemy information into a vertical container.
        VBox enemyBox = new VBox(8);

        // Add the enemy information to the container.
        enemyBox.getChildren().addAll(
                enemyName,
                enemyStatus
        );

        // Align the enemy information to the right.
        enemyBox.setAlignment(Pos.CENTER_RIGHT);

        // Add space from the top and right edges.
        BorderPane.setMargin(
                enemyBox,
                new Insets(40, 60, 0, 0)
        );

        // Return the enemy status container.
        return enemyBox;
    }

    // Create the player status display for combat.
    private VBox createPlayerCombatStatus(Player player) {

        // Create the player name label.
        Label playerName = new Label("Player");

        // Create the player HP label.
        Label combatPlayerStatus = new Label(
                player.getCurrentHP()
                + "/"
                + player.getMaxHP()
                + " HP"
        );

        // Style the player labels.
        playerName.setTextFill(Color.WHITE);
        combatPlayerStatus.setTextFill(Color.WHITE);

        // Set readable font sizes.
        playerName.setStyle(
                "-fx-font-size: 26px; -fx-font-weight: bold;"
        );

        combatPlayerStatus.setStyle(
                "-fx-font-size: 20px;"
        );

        // Put the player information into a vertical container.
        VBox playerBox = new VBox(8);

        // Add the player information to the container.
        playerBox.getChildren().addAll(
                playerName,
                combatPlayerStatus
        );

        // Align the player information to the left.
        playerBox.setAlignment(Pos.CENTER_LEFT);

        // Add space from the bottom and left edges.
        BorderPane.setMargin(
                playerBox,
                new Insets(0, 0, 60, 60)
        );

        // Return the player status container.
        return playerBox;
    }

    // Create the attack button.
    private Button createAttackButton() {

        // Create the attack button.
        Button attackButton = new Button("ATTACK");

        // Set the button dimensions.
        attackButton.setPrefWidth(180);
        attackButton.setPrefHeight(60);

        // Style the attack button.
        attackButton.setStyle(
                "-fx-background-color: #333333;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 22px;"
                + "-fx-font-weight: bold;"
                + "-fx-border-color: white;"
                + "-fx-border-width: 2px;"
                + "-fx-background-radius: 8px;"
                + "-fx-border-radius: 8px;"
                + "-fx-cursor: hand;"
        );

        // Return the configured button.
        return attackButton;
    }

 // Configure the attack button behavior.
    private void setupAttackButton(
            Button attackButton,
            Combat combat,
            Enemy enemy,
            Player player,
            Stage stage,
            VBox playerBox,
            VBox enemyBox) {

        // Get the player HP label from the player status container.
        Label combatPlayerStatus =
                (Label) playerBox.getChildren().get(1);

        // Get the enemy HP label from the enemy status container.
        Label enemyStatus =
                (Label) enemyBox.getChildren().get(1);

        // Perform one combat turn when the button is pressed.
        attackButton.setOnAction(event -> {

            // Perform one combat turn.
            combat.performTurn();

            // Update the enemy HP display.
            enemyStatus.setText(
                    enemy.getCurrentHP()
                    + "/"
                    + enemy.getMaxHP()
                    + " HP"
            );

            // Update the player HP display.
            combatPlayerStatus.setText(
                    player.getCurrentHP()
                    + "/"
                    + player.getMaxHP()
                    + " HP"
            );

            // Check if the enemy was defeated.
            if (enemy.isDead()) {

                // Update the main scene player status.
                updatePlayerStatus();

                // Return to the main scene.
                stage.setScene(mainScene);

            // Check if the player was defeated.
            } else if (player.isDead()) {

                // Show the game over dialog.
                showGameOver(stage);
            }
        });
    }

    // Update the player HP display during combat.
    private void updateCombatStatus(
            Label combatPlayerStatus,
            Enemy enemy) {

        // Update the player HP label.
        combatPlayerStatus.setText(
                player.getCurrentHP()
                + "/"
                + player.getMaxHP()
                + " HP"
        );
    }

    // Show the game over dialog.
    private void showGameOver(Stage stage) {

        // Create the game over dialog.
        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        // Set the dialog title.
        alert.setTitle("Game Over");

        // Set the dialog header.
        alert.setHeaderText("You lost!");

        // Set the dialog message.
        alert.setContentText(
                "The player has been defeated."
        );

        // Close the game after the dialog is closed.
        alert.setOnHidden(event -> {
            stage.close();
        });

        // Show the game over dialog.
        alert.show();
    }

    // Determine whether a random combat encounter should start.
    private boolean shouldStartCombat() {

        // Generate a random number between 1 and the combat chance.
        int randomNumber =
                (int) (Math.random() * COMBAT_CHANCE) + 1;

        // Start combat only when the generated number is 1.
        return randomNumber == 1;
    }
}

