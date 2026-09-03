package de.wagnerj27.simplesword.dungeon;

public class Wall extends Tile {
	
	@Override
	public boolean isWalkable() {
		return false;
	}
}
