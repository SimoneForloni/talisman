package game;

import java.util.Scanner;

import game.model.Player;

public class GameManager {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    boolean running = true;

    while (running) {
      char choice = askMenuChoice();

      switch (choice) {
        case '1' -> {
          Player player = carachterCreator();
          if (player == null) {
            break;
          }
          Game game = new Game(player);
          game.start();
        }
        case '2' -> System.out.println("\n-> Funzionalità Carica non ancora implementata.\n");
        case '3' -> System.out.println("\n-> Impostazioni non ancora implementate.\n");
        case '4' -> {
          System.out.println("\nArrivederci!");
          running = false;
        }
      }

      if (running && choice != '1') {
        pressEnterToContinue();
      }
    }

    scanner.close();
    System.exit(0);
  }

  private static char askMenuChoice() {

    while (true) {
      clearScreen();
      showMenu();

      String input = scanner.nextLine().trim().toUpperCase();

      if (input.length() == 1 && "1234".contains(input)) {
        return input.charAt(0);
      }

      System.out.println("\nInvalid choice. Retry.");
      pressEnterToContinue();
    }
  }

  private static void showMenu() {
    System.out.println("""
        ╔════════════════════════════╗
        ║          TALISMAN          ║
        ╚════════════════════════════╝

              1) New game
              2) Load game
              3) Setting
              4) Quit

        Select a option (1-4):
        """);
  }

  private static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  private static void pressEnterToContinue() {
    System.out.println("Premi INVIO per continuare...");
    scanner.nextLine();
  }

  private static Player carachterCreator() {
    System.out.println("\n=== CHARACTER CREATION ===\n");

    String name;
    
    while (true) {
      System.out.println("Choose your name: ");

      name = scanner.nextLine().trim();
      
      if (name.isBlank()) {
        System.out.println("Invalid name: ");
        continue;
      }

      if (name.equalsIgnoreCase("q")) {
        System.out.println("Character creation cancelled");
        return(null);
      }

      if (name.length() < 2) {
        System.out.println("Character name too short (at least 2 charachters)");
        continue;
      }

      if (name.length() > 20) {
        System.out.println("Character name too long (max 20 charachters)");
        continue;
      }

      break;
    }

    System.out.println("""
        ╔════════════════════════════╗
        ║          TALISMAN          ║
        ╚════════════════════════════╝

              1) Warrior
              2) Wizarx
              3) Thief

        Select a option (1-4):
        """);

    int choice = readNumber(1, 3);

    String classType = switch (choice) {
      case 1 -> "warrior";
      case 2 -> "wizard";
      default -> "thief";
    };

    Player p = new Player(name, classType);

    System.out.println(p);

    System.out.println("You can't change your class once confirmed. Do you confirm? (y/n): ");
    if (!scanner.nextLine().trim().toLowerCase().startsWith("s")) {}

    return p;
  }

  private static int readNumber(int min, int max) {
    while (true) {
      try {
        int n = Integer.parseInt(scanner.nextLine().trim());
        if (n >= min && n <= max) return n;
        System.out.println("Pick a number between " + min + " and " + max);
      } catch (NumberFormatException e) {
        System.out.println("Pick a valid number.");
      }
    }
  }
}