package com.suzyq.rpg;

import com.suzyq.rpg.models.Player;
import com.suzyq.rpg.services.InitialiseGame;
import com.suzyq.rpg.services.PlayerCreator;

import java.io.File;
import java.util.Scanner;

import static com.suzyq.rpg.game.AdvanceTime.advanceTime;
import static com.suzyq.rpg.utils.GameSaveManager.loadGame;
import static com.suzyq.rpg.utils.GameSaveManager.saveGame;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the RPG CLI!");

        Player player = InitialiseGame.start(scanner);
        if (player == null) return;

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Adventure (advance time)");
            System.out.println("2. Rest");
            System.out.println("3. Check health and inventory");
            System.out.println("4. Save Game");
            System.out.println("5. Exit");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    advanceTime(player, scanner);
                    break;
                case "2":
                    player.rest();
                    break;
                case "3":
                    player.getHealth();
                    player.getHealingPotions();
                    break;
                case "4":
                    saveGame(player);
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}