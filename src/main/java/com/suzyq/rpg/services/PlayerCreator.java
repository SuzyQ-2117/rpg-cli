package services;

import models.Knight;
import models.Mage;
import models.Player;
import models.Rogue;

import java.util.Scanner;

public class PlayerCreator {

    public static Player createNewPlayer(Scanner scanner) {
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Choose your class:");
        System.out.println("1. Mage");
        System.out.println("2. Rogue");
        System.out.println("3. Knight");

        while (true) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    return new Mage(name);
                case "2":
                    return new Rogue(name);
                case "3":
                    return new Knight(name);
                default:
                    System.out.print("Invalid choice. Please enter 1, 2, or 3: ");
            }
        }
    }
}
