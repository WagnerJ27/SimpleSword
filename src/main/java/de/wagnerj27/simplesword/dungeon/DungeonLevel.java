package de.wagnerj27.simplesword.dungeon;

public class DungeonLevel {
	private int height;
	private int width;
	private Tile[][] tiles;
	
	
	public DungeonLevel(int height, int width, int level) {
		this.height= height;
		this.width = width;
		if(level ==1) {
			initializeLevelOne();
		}
		if(level ==2) {
			intitializeLevelTwo();
		}
		if(level ==3) {
			initializeLevelThree();
		}
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

	    // Wall from (18,10) to (18,19).
	    for (int i = 10; i <= 19; i++) {

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

	    // Wall from (18,31) to (18,39).
	    for (int i = 31; i <= 39; i++) {

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
	    
	   //Place stairs to level 2
	    tiles[17][47] = new Stair();
	}
	
	private void intitializeLevelTwo() {
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
	    
	    //Horizontal Walls
	    
	    //Wall from (5,1) to (5,15) with a floor at (5,7)
	    for(int i=1; i<=15;i++){
	    	if(i==7) {
	    		continue;
	    	}
	    	tiles[5][i] = new Wall();
	    	
	    }
	    
	    //Wall from (10,7) to (10,15)
	    for(int i=7; i<=15;i++){
	    	
	    	tiles[10][i] = new Wall();
	    	
	    }
	    
	    //Wall from (14,1) to (14,7)
	    for(int i=1; i<=7;i++){
	    	
	    	tiles[14][i] = new Wall();
	    	
	    }
	    
	    //Wall from (18,1) to (18,15)
	    for(int i=1; i<=15;i++){
	    	
	    	tiles[18][i] = new Wall();
	    	
	    }
	    
	    //Wall from (3,23) to (3,47) with floors at (3,30) to (3,32) and (3,42) (3,44)
	    for(int i=23; i<=47;i++){
	    	if((i>=30 && i<=32) || (i>=42 && i<=44)) {
	    		continue;
	    	}
	    	tiles[3][i] = new Wall();
	    	
	    }
	    
	    
	    //Wall from (9,23) to (9,31)
	    for(int i=23; i<=31;i++){
	    	
	    	tiles[9][i] = new Wall();
	    	
	    }
	    
	    //Wall from (12,23) to (12,30)
	    for(int i=23; i<=30;i++){
	    	
	    	tiles[12][i] = new Wall();
	    	
	    }
	    
	    //Wall from (12,39) to (12,47) and Wall from (6,39) to (6,47)
	    for(int i=39; i<=47;i++){
	    	
	    	tiles[12][i] = new Wall();
	    	tiles[6][i] = new Wall();
	    }
	    
	    //Wall from (18,23) to (18,39)
	    for(int i=23; i<=39;i++){
	    	
	    	tiles[18][i] = new Wall();
	    	
	    }
	    
	    //Wall from (14,31) to (14,39)
	    for(int i=31; i<=39;i++){
	    	
	    	tiles[14][i] = new Wall();
	    	
	    }
	    
	    //Wall from (16,39) to (16,47)
	    for(int i=39; i<=47;i++){
	    	
	    	tiles[16][i] = new Wall();
	    	
	    }
	    
	    
	    //Vertikal Walls
	    
	    
	    
	    //Wall from (1,15) to (4,15)
	    for(int i=1; i<=4;i++){
	    	
	    	tiles[i][15] = new Wall();
	    	
	    }
	    
	    //Wall at (8,15) and (9,15)
	    tiles[8][15] = new Wall();
	    tiles[9][15] = new Wall();
	    
	    //Walls from (12,15) to (14,15)
	    tiles[12][15] = new Wall();
	    tiles[13][15] = new Wall();
	    tiles[14][15] = new Wall();
	    
	    
	    //Walls at (16,15) and (17,15)
	    tiles[16][15] = new Wall();
	    tiles[17][15] = new Wall();
	    
	    
	    //Wall from (4,23) to (17,23)
	    for(int i=4; i<=17;i++){
	    	
	    	tiles[i][23] = new Wall();
	    	
	    }
	    
	    //Walls from (6,31) to (8,31)
	    tiles[6][31] = new Wall();
	    tiles[7][31] = new Wall();
	    tiles[8][31] = new Wall();
	    
	    //Wall at (15,31) and (16,31)
	    tiles[15][31] = new Wall();
	    tiles[16][31] = new Wall();
	    
	    
	    //Wall at (15,39) and (17,39)
	    tiles[15][39] = new Wall();
	    tiles[17][39] = new Wall();
	    
	    
	    //Splace Staits to next level at (15,32)
	    tiles[15][32] = new Stair();
	}
	
	private void initializeLevelThree() {
		tiles = new Tile[height][width];
		
		//Place outer Walls and floor
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
	    
	    //Place DungeonExit at (3,13)
	    tiles[3][13] = new DungeonExit();
	    
		//Playe Boss at (3,12)
	    tiles[3][12]  = new BossTile();
	}
	
	public boolean isInside(int x, int y) {
			if((y>=0 && y< height) && (x>=0 && x< width)) {
				return true;
			}
		return false;
	}
	
	public boolean isStairs(int x, int y) {
	    return getTile(x, y) instanceof Stair;
	}
	
	public boolean isDungeonExit(int x, int y) {
		return getTile(x,y) instanceof DungeonExit;
	}
	
	public boolean isBossTile(int x,int y) {
		return getTile(x,y) instanceof BossTile;
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
