package de.wagnerj27.simplesword.entities;

public class Enemy {
	private int rewardEXP;
	private int maxHP;
	private int currentHP;
	private int strength;
	private int defense;
	private String name;
	
	public Enemy(int randomEnemy) {
	if(randomEnemy == 1) {
		initializeBat();
	}
	if(randomEnemy == 2) {
		initializeSnake();
	}
	if(randomEnemy == 3) {
		initializeSpider();
	}
	if(randomEnemy == 4) {
		initializeZombie();
	}
	if(randomEnemy == 5) {
		initializeGoblin();
	}
	if(randomEnemy == 6) {
		initializeGoblinKing();
	}
	}
	
	
	
	private void initializeBat() {
		name = "Bat";
		rewardEXP = 10;
		maxHP = 10;
		currentHP = 10;
		strength = 7;
		defense = 2;
	}

	public void initializeSnake() {
		name = "Snake";
		rewardEXP = 10;
		maxHP = 12;
		currentHP = 12;
		strength = 6;
		defense = 3;
	}
	
	private void initializeSpider() {
		name = "Spider";
		rewardEXP = 10;
		maxHP = 11;
		currentHP = 11;
		strength = 7;
		defense = 1;
	}
	
	private void initializeZombie() {
		name = "Zombie";
		rewardEXP = 10;
		maxHP = 10;
		currentHP = 10;
		strength = 14;
		defense = 5;
	}
	
	private void initializeGoblin() {
		name = "Goblin";
		rewardEXP = 10;
		maxHP = 10;
		currentHP = 10;
		strength = 15;
		defense = 6;
	}
	
	private void initializeGoblinKing() {
		name = "GoblinKing";
		rewardEXP = 50;
		maxHP = 50;
		currentHP = 50;
		strength = 15;
		defense = 8;
	}

		public String getName() {
			return name;
		}
		
		public int getRewardEXP() {
			return rewardEXP;
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
		
		public void takeDamage(Player player) {
			if(player.getStrength() - defense <=0) {
				this.currentHP -=1;
			}else {
				this.currentHP = currentHP - (player.getStrength() - defense);
			}
			
		}
	
		    
		
		public boolean isDead() {
			if(currentHP <=0) {
				return true;
			}else {
				return false;
			}
		}
}
	
	

