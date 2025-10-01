package game;

import models.Enemy;
import models.Player;

import java.util.Scanner;

import static game.EnemySpawning.spawnEnemy;

public class AdvanceTime {
    // advanceTime() always spawns a scaled enemy
    public static void advanceTime(Player player, Scanner scanner) {
        System.out.println("\n--- A new encounter begins ---");
        Enemy enemy = spawnEnemy(player);

        System.out.println("A wild " + enemy.getName() + " appears!");

        // Player always goes first for user input
        while (player.isAlive() && enemy.isAlive()) {
            // Player's turn: prompt for action
            System.out.println(player.getName() + " has " + player.getHealth() + "/" + player.getMaxHealth() + ".");
            System.out.println("Choose your action: 1. Attack  2. Defend  3. Use Healing Potion");
            String action = scanner.nextLine().trim();
            switch (action) {
                case "1":
                    player.attack(enemy);
                    break;
                case "2":
                    player.defend();
                    break;
                case "3":
                    player.useHealingPotion();
                    break;
                default:
                    System.out.println("Invalid action. Defaulting to attack.");
                    player.attack(enemy);
            }
            if (!enemy.isAlive()) break;
            // Enemy's turn: always attack
            enemy.attack(player);
        }

        if (player.isAlive()) {
            System.out.println("You defeated the enemy!");
            int xp = player.getXP();
            System.out.println("You gained " + xp + " XP.");
            player.gainXP(xp);
        } else {
            System.out.println("You have been defeated... Game over!");
            System.exit(0);
        }
    }
}
