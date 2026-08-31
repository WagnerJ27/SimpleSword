package de.wagnerj27.simplesword;

public class Combat {
	Player player;
	Enemy enemy;
	
	public Combat(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}
	
	public void performTurn() {

	    if (player.isDead()) {
	        return;
	    }
	    playerAttack();
	    if (enemy.isDead()) {
	    	player.getRewardEXP(enemy);
	    	player.levelUp();
	        return;
	    }
	    enemyAttack();
	}
	
	private void playerAttack() {
		enemy.takeDamage(player);
	}
	
	private void enemyAttack() {
		player.takeDamage(enemy);
	}
}
