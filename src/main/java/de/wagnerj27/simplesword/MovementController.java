package de.wagnerj27.simplesword;

public class MovementController {
	
	public void moveForward(Player player, Room room) {
		Direction direction = player.getDirection();
		Position position = player.getPosition();
		int x = position.getX();
		int y = position.getY();

		int targetX;
		int targetY;
			
		
		
		switch(direction) {
			case NORTH:
				targetX =x;
				targetY =y-1;			
				break;
			
			case EAST:
				targetX = x+1;
				targetY = y;
				break;
				
			case SOUTH:
				targetX = x;
				targetY = y+1;
				break;
				
			case WEST:
				targetX =x-1;
				targetY = y;
				break;
				
			default:
				targetX =x;
				targetY =y;
		}
		if(room.isInside(targetX, targetY)) {
			Tile tile = room.getTile(targetX, targetY);

			
			if(tile.isWalkable()) {
				player.setPosition(targetX, targetY);
			}
		}
	}
}
