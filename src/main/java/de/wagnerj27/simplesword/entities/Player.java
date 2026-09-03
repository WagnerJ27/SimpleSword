package de.wagnerj27.simplesword.entities;

import de.wagnerj27.simplesword.Position;
import de.wagnerj27.simplesword.movement.Direction;

public class Player {
	private Position playerPosition;
	private Direction playerDirection;
	private int level;
	private int exp;
	private int maxHP;
	private int currentHP;
	private int strength;
	private int defense;
	
	public Player(int x, int y) {
		playerPosition = new Position(x,y);
		playerDirection = Direction.EAST;
		level = 1;
		exp = 0;
		maxHP = 20;
		currentHP = 20;
		strength = 5;
		defense = 5;
		
	}
	
	public Position getPosition() {
		return playerPosition;
	}
	
	public Direction getDirection() {
		return playerDirection;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getEXP() {
		return exp;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public void takeDamage(Enemy enemy) {
		if(enemy.getStrength() - defense<=0) {
			return;
		}else {
			currentHP = currentHP - (enemy.getStrength() -defense);
		}
	}
	
	public void getRewardEXP(Enemy enemy) {
		exp += enemy.getRewardEXP();
	}
	
	public void levelUp() {
		if(exp >=30) {
			level +=1;
			exp = 0;
			strength +=2;
			defense +=2;
			maxHP +=5;
			currentHP = maxHP;
		}else {
			return;
		}
	}
	
	
	public boolean isDead() {
		if(currentHP <=0) {
			return true;
		}else {
			return false;
		}
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
