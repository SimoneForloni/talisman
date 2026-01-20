package game.service.factories;

import java.util.Random;

import game.model.Enemy;

public class EnemyFactory {
  public static final Random random = new Random();

  public static Enemy createRandomEnemy() {
    int roll = random.nextInt(100);

    if (roll < 50) {
      // 50% probabilità: Nemico debole
      return new Enemy("Goblin", 20, 3, 1, 5, 2);
    } else if (roll < 85) {
      // 35% probabilità: Nemico medio
      return new Enemy("Orc", 40, 6, 3, 15, 10);
    } else {
      // 15% probabilità: Mini-boss
      return new Enemy("Troll", 80, 10, 5, 50, 30);
    }
  }
}