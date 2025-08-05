import models.Player;
import services.PlayerCreator;

import java.io.File;
import java.util.Scanner;

import static game.AdvanceTime.advanceTime;
import static utils.Constants.SAVE_FILE;
import static utils.GameSaveManager.loadGame;
import static utils.GameSaveManager.saveGame;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player;

    public static void main(String[] args) {
        System.out.println("Welcome to the RPG CLI!");

        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            System.out.print("Saved character found. Load it? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                Player loaded = loadGame();
                if (loaded != null) {
                    player = loaded;
                    System.out.println("Loaded saved character: " + player.getName() + " (Level " + player.getLevel() + ")");
                } else {
                    System.out.println("Failed to load save file. Exiting to prevent overwriting.");
                    return;
                }
            } else {
                System.out.print("This will overwrite your save. Are you sure? (yes/no): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                    player = PlayerCreator.createNewPlayer(scanner);
                    System.out.println(player);
                    saveGame(player);
                } else {
                    System.out.println("Exiting.");
                    return;
                }
            }
        } else {
            player = PlayerCreator.createNewPlayer(scanner);
            saveGame(player);
        }

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