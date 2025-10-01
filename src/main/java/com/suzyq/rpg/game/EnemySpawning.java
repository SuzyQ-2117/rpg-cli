package com.suzyq.rpg.game;

import com.suzyq.rpg.models.Enemy;
import com.suzyq.rpg.models.Player;

public class EnemySpawning {

    //spawnEnemy makes more sense to be in the main method as this is more of a game mechanic than an enemy method
    static Enemy spawnEnemy(Player player) {
        // choice of random enemy types
        String[] names = {"Goblin", "Bandit", "Warg", "Skeleton"};
        // choice of multipliers
        double[] healthMultipliers = {0.9, 1.0, 1.1};
        double[] attackMultipliers = {0.8, 1.0, 1.2};

        String name = names[(int) (Math.random() * names.length)];
        // this will get a random value from the array and apply it to the character's stats
        double health = player.getMaxHealth() * healthMultipliers[(int) (Math.random() * healthMultipliers.length)];
        double attack = player.getAttackPower() * attackMultipliers[(int) (Math.random() * attackMultipliers.length)];

        return new Enemy(name, (int) health, (int) attack);
    }
}

