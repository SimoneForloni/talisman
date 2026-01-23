package game.model.board.cards;

import game.model.Enemy;
import game.model.Player;
import game.service.managers.CombatManager;

public class EnemyCard implements AdventureCard {
    protected Enemy enemy;

    public EnemyCard(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void execute(Player player) {
        System.out.printf("You encounter a %s!\n", enemy.getName());
        new CombatManager().startBattle(player, enemy);
    }

    @Override
    public String getName() {
        return enemy.getName();
    }

    @Override
    public CardType getType() {
        return CardType.ENEMY;
    }
}
