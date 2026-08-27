package de.wagnerj27.simplesword;

public class Wall extends Tile {
	
	@Override
	public boolean isWalkable() {
		return false;
	}
}
