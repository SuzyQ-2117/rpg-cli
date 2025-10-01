package com.suzyq.rpg.models;

public class Rogue extends Player {
    public Rogue(String name) {
        super(name, 1, 100, 0, 100, 12, 7);  // Defaults: level 1, health 100, 15 attack, 5 defense

    }

    @Override
    public void attack(Enemy target) {
        stealthAttack(target);
    }

    @Override
    public void defend() {
        System.out.println(name + " dodges quickly!");
    }

//    @Override
    public void stealthAttack(Enemy target) {
        System.out.println(name + " slices " + target.getName() + " with a dagger...");
        target.takeDamage(attackPower);
    }
}