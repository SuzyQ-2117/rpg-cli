package models;

public class Enemy {
    private String name;
    private int health;
    private int attackPower;

    public Enemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " takes " + damage + " damage!");
        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Player target) {
        double modifier = Math.random() > 0.8 ? 1.0 : 0.7;
        int damage = (int) (attackPower * modifier);
        System.out.println(name + " attacks " + target.getName() + "!");
        target.takeDamage(damage);
    }

}