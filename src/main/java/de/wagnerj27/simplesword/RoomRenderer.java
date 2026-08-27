package de.wagnerj27.simplesword;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class RoomRenderer {

    private Room room;
    private Canvas canvas;
    private int tileSize;
    private GraphicsContext graphicsContext;
    private Player player;
    
    
    public RoomRenderer(Room room, Canvas canvas, int tileSize, Player player) {
        this.room = room;
        this.canvas = canvas;
        this.tileSize = tileSize;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.player = player;
        
    }
    

    public void render() {
    	int height = room.getHeight();
    	int width = room.getWidth();
    	int x;
    	int y;
    	
    	int pixelX;
    	int pixelY;
    	
    	for(int i = 0; i < height; i++) {
    	    y=i;
    	    pixelY = y*tileSize;
    		for(int j = 0; j < width; j++) {
    	      x=j;
    	      pixelX = x*tileSize;
    			Tile tile =  room.getTile(j, i);
    			if(tile instanceof Wall) {
    				graphicsContext.setFill(Color.BLACK);
    				graphicsContext.fillRect(pixelX, pixelY, tileSize, tileSize);
    			}else {
    				graphicsContext.setFill(Color.WHITE);
    				graphicsContext.fillRect(pixelX, pixelY, tileSize, tileSize);
    			}
    			
    	    }
    	}
    	Position playerPosition = player.getPosition();
    	Direction playerDirection = player.getDirection();
    	int playerX = playerPosition.getX();
    	int playerY = playerPosition.getY();
    	int shift = (tileSize - tileSize/2) /2;
    	int pixelPlayerX = playerX * tileSize + shift;
    	int pixelPlayerY = playerY * tileSize + shift;
		graphicsContext.setFill(Color.BLUE);
		graphicsContext.fillRect(pixelPlayerX, pixelPlayerY, tileSize/2, tileSize/2);
		
		int playerCenterX = pixelPlayerX + tileSize / 4;
		int playerCenterY = pixelPlayerY + tileSize / 4;
		switch(playerDirection) {
			case NORTH:
				graphicsContext.strokeLine(playerCenterX, playerCenterY, playerCenterX, playerCenterY - tileSize / 4);
				break;
				
			case EAST:
				graphicsContext.strokeLine(playerCenterX, playerCenterY, playerCenterX + tileSize/4, playerCenterY);
				break;
				
			case SOUTH:
				graphicsContext.strokeLine(playerCenterX, playerCenterY, playerCenterX, playerCenterY +tileSize/4);
				break;
				
			case WEST:
				graphicsContext.strokeLine(playerCenterX, playerCenterY, playerCenterX - tileSize/4, playerCenterY);
				break;
		}
    	
    }
    
}