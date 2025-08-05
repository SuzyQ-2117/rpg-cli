package services;

import models.Player;
import utils.Constants;
import utils.GameSaveManager;

import java.io.File;
import java.util.Scanner;

public class InitialiseGame {

    public static Player start(Scanner scanner) {
        File saveDir = new File(Constants.SAVE_DIRECTORY);
        if (!saveDir.exists()) saveDir.mkdirs();

        mainLoop:
        while (true) {
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit Game");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            Player player = null;

            switch (choice) {
                case "1":
                    player = PlayerCreator.createNewPlayer(scanner);
                    GameSaveManager.saveGame(player);
                    return player;

                case "2":
                    File[] saves = saveDir.listFiles((dir, name) -> name.endsWith(Constants.SAVE_EXTENSION));
                    if (saves == null) saves = new File[0];

                    if (saves.length == 0) {
                        System.out.print("No save files exist. Create a new game? (y/n): ");
                        String newGameChoice = scanner.nextLine().trim().toLowerCase();
                        if (newGameChoice.startsWith("y")) {
                            player = PlayerCreator.createNewPlayer(scanner);
                            GameSaveManager.saveGame(player);
                            return player;
                        } else {
                            System.out.println("Returning to main menu...");
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("=== Saved games ===");

                        System.out.println("Please choose an option:");
                        System.out.println("0. Return to main menu");

                        for (int i = 0; i < saves.length; i++) {
                            String name = saves[i].getName().replace(Constants.SAVE_EXTENSION, "");
                            System.out.println((i + 1) + ". " + name);
                        }

                        String saveChoicestr = scanner.nextLine().trim();
                        int saveChoice;

                        try {
                            saveChoice = Integer.parseInt(saveChoicestr);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid selection. Please enter a number matching the save you want to load.");
                            continue;
                        }

                        if (saveChoice == 0) {
                            System.out.println("Returning to the main menu...");
                            continue mainLoop;
                        }

                        if (saveChoice >= 1 && saveChoice <= saves.length) {
                            player = GameSaveManager.loadGame(saves[saveChoice - 1].getName());
                            if (player != null) {
                                System.out.println("Loaded saved character: " + player.getName() +
                                        " (Level " + player.getLevel() + ")");
                                return player;
                            } else {
                                System.out.println("Failed to load save.");
                            }
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                case "3":
                    System.out.println("Thanks for playing! Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection.");
                    break;
            }
        }
    }
}