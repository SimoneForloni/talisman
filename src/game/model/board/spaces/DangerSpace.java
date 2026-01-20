package game.model.board.spaces;

import game.model.Enemy;
import game.model.Player;
import game.service.factories.EnemyFactory;
import game.service.managers.CombatManager;

public class DangerSpace extends Space {
  public DangerSpace(String enemyName, String enemyDescription) {
    super(enemyName, enemyDescription);
  }

  @Override
  public void onLand(Player player) {
    Enemy enemy = EnemyFactory.createRandomEnemy();
    CombatManager.startCombat(player, enemy);
  }
}