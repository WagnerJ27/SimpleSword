package de.wagnerj27.simplesword;

public class Room {
	private int height;
	private int width;
	private Tile[][] tiles;

	
	public Room(int height, int width) {
		this.height= height;
		this.width = width;
		initializeRoom();
	}
	

	
	private void initializeRoom() {
		tiles = new Tile[height][width];
		
		for(int i =0;i<height;i++) {
			for(int j =0;j<width;j++) {
				if(i==0 || i==height-1) {
					tiles[i][j] = new Wall();
				}
				else if(j==0 || j==width-1) {
					tiles[i][j]= new Wall();
				}else  {
					tiles[i][j] = new Floor();
				}
			}
		}
		tiles[2][3] = new Wall();
		tiles[2][4] = new Wall();
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
