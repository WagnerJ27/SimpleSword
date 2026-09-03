package de.wagnerj27.simplesword.movement;

import de.wagnerj27.simplesword.Position;
import de.wagnerj27.simplesword.dungeon.DungeonLevel;
import de.wagnerj27.simplesword.dungeon.Tile;
import de.wagnerj27.simplesword.entities.Player;

public class MovementController {
	
	public boolean moveForward(Player player, DungeonLevel room) {
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
				return true;
			}
			
		}
		return false;
	}
}
