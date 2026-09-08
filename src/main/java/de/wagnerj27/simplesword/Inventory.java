package de.wagnerj27.simplesword;

public class Inventory {

    private int healingPotions;

    public Inventory() {

        healingPotions = 1;

    }

    public void addHealingPotion() {

        healingPotions++;

    }

    public boolean removeHealingPotion() {

        if (healingPotions <= 0) {
            return false;
        }

        healingPotions--;

        return true;
    }

    public int getHealingPotions() {

        return healingPotions;

    }
}
