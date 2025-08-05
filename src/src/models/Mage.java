package models;

public class Mage extends Player {
    public Mage(String name) {
        super(name, 1, 25, 0, 20, 7, 2);  // Defaults: level 1, health 100, 15 attack, 5 defense
    }

    @Override
    public void attack(Enemy target) {
        castSpell(target);
    }

    @Override
    public void defend() {
        System.out.println(name + " raises a magic barrier!");
    }

//    @Override
    public void castSpell(Enemy target) {
        String spell = "Lightning Bolt";
        System.out.println(name + " attacks " + target.getName() + " with " + spell + "!");
        target.takeDamage(attackPower);
    }
}