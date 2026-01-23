package game.model.board.cards;

import game.model.Enemy;
import game.model.Player;

public class BossCard extends EnemyCard {
    public BossCard(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void execute(Player player) {
        System.out.println("!!! BOSS BATTLE !!!");
        super.execute(player);
    }
}
