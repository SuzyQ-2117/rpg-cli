package com.suzyq.rpg.models;

import java.io.Serializable;

public abstract class Player implements Serializable {
    protected String name;
    protected int level;
    protected int health;
    protected int maxHealth;
    protected int experiencePoints;
    protected int xpToNextLevel;
    protected int attackPower;
    protected int defensePower;
    protected int healingPotions = 3;
    protected boolean isDefending = false;

    public Player(String name, int level, int health, int experiencePoints, int xpToNextLevel, int attackPower, int defensePower) {
        this.name = name;
        this.level = level;
        this.health = health;
        this.maxHealth = health;
        this.experiencePoints = experiencePoints;
        this.xpToNextLevel = xpToNextLevel;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    public abstract void attack(Enemy target);

    public void defend() {
        isDefending = true;
        System.out.println(name + " is preparing to defend against the next attack!");
    }

    public void setDefending(boolean defending) {
        this.isDefending = defending;
    }

    public void gainXP(int amount) {
        experiencePoints += amount;
        System.out.println(name + " earned " + amount + "XP!");

        while (experiencePoints >= xpToNextLevel) {
            experiencePoints -= xpToNextLevel;
            levelUp();
            xpToNextLevel *= 1.15;
        }
    }

    public void levelUp() {
        level++;
        System.out.println(name + " is now level " + level + "!");
        attackPower *= 1.1;
        defensePower = (int)(defensePower * 1.1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        System.out.println(name + " has " + health + " health points.");
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public void getHealingPotions() {
        System.out.println(name + " has " + healingPotions + " healing potions left.");
    }

    public void setHealingPotions(int healingPotions) {
        this.healingPotions = healingPotions;
    }

    public int getXP() {
        int min = (int) (xpToNextLevel * 0.3);
        int max = (int) (xpToNextLevel * 0.5);
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public void takeDamage(int damage) {
        int adjustedDefense = isDefending ? (int)(getDefensePower() * 1.5) : getDefensePower();
        int actualDamage = Math.max(0, damage - adjustedDefense);
        isDefending = false;
        health -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage!");
        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        }
    }

    public void rest() {
        this.health = maxHealth;
        refillHealingPotions();
        System.out.println(name + " takes a long rest and recovers health.");
    }

    private void refillHealingPotions() {
        setHealingPotions(3);
        System.out.println("Healing potions refilled.");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void useHealingPotion() {
        if (healingPotions <= 0) {
            System.out.println(name + " has no healing potions left! ");
            return;
        }

        if (health < maxHealth) {
            int healedAmount = (int) Math.min(maxHealth * 0.4, maxHealth - health);
            health += healedAmount;
            healingPotions--;
            System.out.println(name + " restores " + healedAmount + " health.");
        } else {
            System.out.println(name + " is already at full health.");
        }
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", health=" + health +
                ", maxHealth=" + maxHealth +
                ", experiencePoints=" + experiencePoints +
                ", xpToNextLevel=" + xpToNextLevel +
                ", attackPower=" + attackPower +
                ", defensePower=" + defensePower +
                ", healingPotions=" + healingPotions +
                '}';
    }
}