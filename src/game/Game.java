package game;

import game.model.Player;
import game.util.Methods;

public class Game {
  private Player player;

  Game(Player player) {
    setPlayer(player);
  }

  // ===========================================================
  // GETTER
  // ===========================================================

  public Player getPlayer() {
    return player;
  }

  // ===========================================================
  // SETTER
  // ===========================================================

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void start() {
    Methods.clearScreen();
  }
}
