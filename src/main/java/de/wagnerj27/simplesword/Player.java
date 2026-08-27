package de.wagnerj27.simplesword;

public class Player {
	private Position playerPosition;
	private Direction playerDirection;
	
	
	public Player(int x, int y) {
		playerPosition = new Position(x,y);
		playerDirection = Direction.NORTH;
		
	}
	
	public Position getPosition() {
		return playerPosition;
	}
	
	public Direction getDirection() {
		return playerDirection;
	}
	
	
	public void setPosition(int x, int y) {
		playerPosition.setPosition(x, y);
	}
	public void turnLeft() {
		switch(playerDirection) {
			case NORTH:
				playerDirection = Direction.WEST;
				break;
				
			case EAST:
				playerDirection = Direction.NORTH;
				break;
				
			case SOUTH:
				playerDirection = Direction.EAST;
				break;
				
			case WEST:
				playerDirection = Direction.SOUTH;
				break;
		}
	}
	
	
	public void turnRight() {
		
		switch(playerDirection) {
			case NORTH:
				playerDirection = Direction.EAST;
				break;
				
			case EAST:
				playerDirection = Direction.SOUTH;
				break;
				
			case SOUTH:
				playerDirection = Direction.WEST;
				break;
				
			case WEST:
				playerDirection = Direction.NORTH;
				break;
		}
	}
	
	
	
}
