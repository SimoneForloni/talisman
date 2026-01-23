package game.service.factories;

import java.util.Random;
import game.model.Enemy;
import game.model.StatusEffect;
import game.model.board.cards.AdventureCard;
import game.model.board.cards.BossCard;
import game.model.board.cards.EnemyCard;
import game.model.board.cards.EventCard;
import game.model.board.cards.ItemCard;
import game.model.board.spaces.*;
import game.util.Constants;

public class AdventureDeck {
    private static final Random random = new Random();

    public AdventureCard drawCard() {
        int roll = random.nextInt(100);

        if (roll < 40) {
            // 40% Enemy
            return new EnemyCard(EnemyFactory.createRandomEnemy());
        } else if (roll < 60) {
            // 20% Item
            // Nota: InventoryObject richiederebbe un costruttore reale. Qui simulo.
            return new ItemCard("Potion", null); 
        } else if (roll < 90) {
            // 30% Event
            return createRandomEvent();
        } else {
            // 10% Boss
            return new BossCard(new Enemy("Dragon", 100, 15, 10, 100, 100));
        }
    }

    private EventCard createRandomEvent() {
        int roll = random.nextInt(3);
        switch (roll) {
            case 0:
                return new EventCard("Poison Trap", p -> {
                    System.out.println("You stepped on a trap!");
                    p.addStatusEffect(StatusEffect.POISONED);
                });
            case 1:
                return new EventCard("Found Gold", p -> {
                    System.out.println("You found a hidden stash!");
                    p.setCoins(p.getCoins() + 5);
                });
            default:
                return new EventCard("Teleport", p -> {
                    System.out.println("A magical wind blows you away!");
                    p.setPosition(random.nextInt(Constants.BOARD_SIZE));
                });
        }
    }
}