package game.model.board.spaces;

import game.model.Enemy;
import game.model.Player;
import game.service.factories.EnemyFactory;
import game.service.managers.CombatManager;

public class DangerSpace extends Space {

    public DangerSpace(String name, String description) {
        super(name, description);
    }

    @Override
    public void onLand(Player player) {
        if (!player.isAlive()) {
            return;
        }

        System.out.println("An enemy appears!");
        Enemy enemy = EnemyFactory.createRandomEnemy();
        System.out.printf("A wild %s with %d HP appears!\n\n", enemy.getName(), enemy.getHp());

        new CombatManager().startBattle(player, enemy);
    }
}