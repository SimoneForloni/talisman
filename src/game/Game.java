package game;

import game.model.Player;
import game.model.board.Board;
import game.model.board.spaces.Space;
import game.service.loggers.ConsoleLogger;
import game.service.loggers.GameLogger;
import game.service.managers.CombatManager;
import game.util.Methods;

public class Game {
  private final Player player;
  private final Board board;
  private final Deck deck;
  private final GameLogger logger;
  private final CombatManager combatManager;
  private boolean isRunning;

  public Game(Player player) {
    this(player, new ConsoleLogger());
  }

  public Game(Player player, GameLogger logger) {
    this.player = player;
    this.logger = logger;
    this.combatManager = new CombatManager(logger);
    this.deck = new Deck(logger);
    this.board = new Board(deck, logger, combatManager);
    this.isRunning = false;
  }

  /**
   * Ciclo principale per CLI.
   * Qui gestiamo il clearScreen e il pressEnter per dare ritmo al gioco.
   */
  public void start() {
    isRunning = true;

    while (isRunning && player.isAlive()) {
      Methods.clearScreen(); // Pulisce all'inizio di ogni turno
      showCLIMenu();
      handleCLIInput();

      // Se non abbiamo chiuso il gioco, aspettiamo che l'utente legga prima di pulire
      // di nuovo
      if (isRunning && player.isAlive()) {
        Methods.pressEnterToContinue();
      }
    }

    if (!player.isAlive()) {
      Methods.clearScreen();
      handleGameOver();
    }
  }

  // --- LOGICA CORE (Indipendente dall'interfaccia) ---

  public void movePlayer() {
    if (!player.isAlive())
      return;

    logger.log("Rolling dice...");
    int dice = (int) (Math.random() * 6) + 1;
    logger.log("Result: [" + dice + "]");

    int newPos = board.calculateNewPosition(player.getPosition(), dice);
    player.setPosition(newPos);

    Space currentSpace = board.getSpace(player);
    logger.log("\n>>> Landed on: " + currentSpace.getName() + " <<<");
    logger.log(currentSpace.getDescription());

    currentSpace.onLand(player);

    // La GUI chiamerà questo metodo e vedrà i log apparire.
    // La CLI chiama questo e poi aspetta il "Press Enter" nel metodo start().
  }

  public void showInventory() {
    logger.log("\n========= INVENTORY =========");
    if (player.getInventory().isEmpty()) {
      logger.log("Empty");
    } else {
      player.getInventory().forEach(item -> logger.log("- " + item.toString()));
    }
  }

  public void showCharacterSheet() {
    logger.log("\n=========== STATS ==========");
    logger.log(player.toString());
  }

  public void quitGame() {
    logger.log("Exiting game...");
    this.isRunning = false;
  }

  // --- SUPPORTI CLI ---

  private void showCLIMenu() {
    Space currentSpace = board.getSpace(player);
    System.out.println("=".repeat(50));
    System.out.printf(" POSITION: %d | SPACE: %s\n", player.getPosition() + 1, currentSpace.getName());
    System.out.printf(" HP: %d/%d | COINS: %d | XP: %d\n",
        player.getHp(), player.getMaxHp(), player.getCoins(), player.getXp());
    System.out.println("=".repeat(50));
    System.out.println("1) Roll Dice & Move");
    System.out.println("2) Inventory");
    System.out.println("3) Character Stats");
    System.out.println("4) Quit Game");
    System.out.print("\nYour choice: ");
  }

  private void handleCLIInput() {
    int choice = Methods.readNumber(1, 4);
    switch (choice) {
      case 1 -> movePlayer();
      case 2 -> showInventory();
      case 3 -> showCharacterSheet();
      case 4 -> quitGame();
    }
  }

  private void handleGameOver() {
    logger.log("\n" + "#".repeat(40));
    logger.log("           G A M E   O V E R");
    logger.log("#".repeat(40));
    logger.log("\nL'avventura di " + player.getName() + " termina qui.");
    logger.log("Monete: " + player.getCoins() + " | XP: " + player.getXp());
  }
}