package models;

public class Knight extends Player {
    public Knight(String name) {
        super(name, 1, 100, 0, 100, 11, 10);  // Defaults: level 1, health 100, 15 attack, 5 defense

    }

    @Override
    public void attack(Enemy target) {
        if (Math.random() > 0.5) {
            strike(target);
        } else {
            shieldBash(target);
        }
    }

    @Override
    public void defend() {

    }

//    @Override
    public void strike(Enemy target) {
        target.takeDamage(attackPower);
        System.out.println(name + " strikes with their sword.");
    }

//    @Override
    public void shieldBash(Enemy target) {
        target.takeDamage(attackPower);
        System.out.println(name + " bashes with their shield!");
    }
}