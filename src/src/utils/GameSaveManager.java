package utils;

import java.io.*;

import models.Player;

public class GameSaveManager {

    // EXISTING CODE

    // Saves the game progress - only one valid save file at once
    // this will only serialize the player character
    // a game cannot be saved in combat
    public static void saveGame(Player player) {
        File dir = new File(Constants.SAVE_DIRECTORY);
        if(!dir.exists()) dir.mkdirs();

        String fileName = player.getName() + Constants.SAVE_EXTENSION;
        File saveFile = new File(dir, fileName);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(player);
            System.out.println("Game saved successfully as " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to save game. Reason: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // loads in a previously saved game
    public static Player loadGame(String fileName) {
        File saveFile = new File(Constants.SAVE_DIRECTORY, fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved game found or failed to load.");
            return null;
        }
    }






}
