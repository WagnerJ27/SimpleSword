package de.wagnerj27.simplesword.entities;

public class Enemy {
	private int rewardEXP;
	private int maxHP;
	private int currentHP;
	private int strength;
	private int defense;
	private String name;
	
	public Enemy() {
	initializeBat();
	}
	
private void initializeBat() {
	name = "Bat";
	rewardEXP = 10;
	maxHP = 10;
	currentHP = 10;
	strength = 8;
	defense = 2;
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
	
	

