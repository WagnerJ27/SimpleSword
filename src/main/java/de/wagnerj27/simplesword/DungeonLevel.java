package de.wagnerj27.simplesword;

public class DungeonLevel {
	private int height;
	private int width;
	private Tile[][] tiles;

	
	public DungeonLevel(int height, int width) {
		this.height= height;
		this.width = width;
		initializeLevelOne();
	}
	

	
	private void initializeLevelOne() {

	    tiles = new Tile[height][width];

	    // Create the outer border of the level.
	    for (int i = 0; i < height; i++) {

	        for (int j = 0; j < width; j++) {

	            if (i == 0 || i == height - 1) {

	                tiles[i][j] = new Wall();

	            } else if (j == 0 || j == width - 1) {

	                tiles[i][j] = new Wall();

	            } else {

	                tiles[i][j] = new Floor();
	            }
	        }
	    }

	    // Create horizontal walls.

	    // Wall from (5,1) to (5,21).
	    for (int i = 1; i <= 21; i++) {

	        tiles[5][i] = new Wall();
	    }

	    // Wall from (16,1) to (16,10).
	    for (int i = 1; i <= 10; i++) {

	        tiles[16][i] = new Wall();
	    }

	    // Wall from (18,9) to (18,18).
	    for (int i = 9; i <= 18; i++) {

	        tiles[18][i] = new Wall();
	    }

	    // Wall from (13,19) to (13,21) and from (13,25) to (13,31).
	    for (int i = 19; i <= 31; i++) {

	        if (i >= 22 && i <= 24) {

	            continue;
	        }

	        tiles[13][i] = new Wall();
	    }

	    // Wall from (11,32) to (11,40).
	    for (int i = 32; i <= 40; i++) {

	        tiles[11][i] = new Wall();
	    }

	    // Wall from (18,32) to (18,38).
	    for (int i = 32; i <= 38; i++) {

	        tiles[18][i] = new Wall();
	    }

	    // Wall from (16,41) to (16,47).
	    for (int i = 41; i <= 47; i++) {

	        tiles[16][i] = new Wall();
	    }

	    // Create vertical walls.

	    // Wall from (14,19) to (17,19).
	    for (int i = 14; i <= 17; i++) {

	        tiles[i][19] = new Wall();
	    }

	    // Wall at (17,10).
	    tiles[17][10] = new Wall();

	    // Wall from (7,21) to (12,21).
	    for (int i = 7; i <= 12; i++) {

	        tiles[i][21] = new Wall();
	    }

	    // Wall from (2,21) to (4,21).
	    for (int i = 2; i <= 4; i++) {

	        tiles[i][21] = new Wall();
	    }

	    // Wall from (1,31) to (11,31).
	    for (int i = 1; i <= 11; i++) {

	        tiles[i][31] = new Wall();
	    }

	    // Wall from (14,31) to (17,31).
	    for (int i = 14; i <= 17; i++) {

	        tiles[i][31] = new Wall();
	    }

	    // Wall from (1,40) to (9,40) with floor at (4,40) and (6,40).
	    for (int i = 1; i <= 9; i++) {

	        if (i == 4 || i == 6) {

	            continue;
	        }

	        tiles[i][40] = new Wall();
	    }

	    // Wall from (16,40) to (18,40).
	    for (int i = 16; i <= 18; i++) {

	        tiles[i][40] = new Wall();
	    }
	}
	
	
	public boolean isInside(int x, int y) {
			if((y>=0 && y< height) && (x>=0 && x< width)) {
				return true;
			}
		return false;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
