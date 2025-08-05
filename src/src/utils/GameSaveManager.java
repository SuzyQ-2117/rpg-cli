package utils;

import java.io.*;

import models.Player;
import static utils.Constants.SAVE_FILE;

public class GameSaveManager {

    // EXISTING CODE

    // Saves the game progress - only one valid save file at once
    // this will only serialize the player character
    // a game cannot be saved in combat
    public static void saveGame(Player player) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.valueOf(SAVE_FILE)))) {
            oos.writeObject(player);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save game. Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // loads in a previously saved game
    public static Player loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved game found or failed to load.");
            return null;
        }
    }






}
