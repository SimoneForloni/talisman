package game.service.factories;

import java.util.Random;

import game.model.Enemy;
import game.model.StatusEffect;
import game.model.board.cards.AdventureCard;
import game.model.board.cards.BossCard;
import game.model.board.cards.EnemyCard;
import game.model.board.cards.EventCard;
import game.model.board.cards.ItemCard;
import game.model.inventory.InventoryObject;
import game.service.loggers.GameLogger;
import game.util.Constants;

/**
 * Factory per generare istanze casuali di carte avventura.
 * Centralizza la creazione di carte, usando altre factory (ItemFactory,
 * EnemyFactory)
 * per creare i contenuti specifici delle carte.
 */
public class CardFactory {
	private static final Random RANDOM = new Random();

	/**
	 * Crea una carta avventura casuale (es. Nemico, Oggetto, Evento).
	 * 
	 * @return Un'istanza di AdventureCard.
	 */
	public static AdventureCard createRandomAdventureCard(GameLogger logger) {
		int roll = RANDOM.nextInt(100);

		if (roll < 25) {
			// 25% Nemico
			return createRandomEnemyCard();
		} else if (roll < 65) {
			// 40% Oggetto
			return createRandomItemCard();
		} else if (roll < 95) {
			// 30% Evento
			return createRandomEventCard(logger);
		} else {
			// 5% Boss
			return createRandomBossCard();
		}
	}

	public static ItemCard createRandomItemCard() {
		InventoryObject randomItem = ItemFactory.createRandomItem();
		return new ItemCard(randomItem);
	}

	public static EnemyCard createRandomEnemyCard() {
		Enemy randomEnemy = EnemyFactory.createRandomEnemy();
		return new EnemyCard(randomEnemy);
	}

	public static EventCard createRandomEventCard(GameLogger logger) {
		int roll = RANDOM.nextInt(3);
		switch (roll) {
			case 0:
				return new EventCard("Poison Trap", p -> {
					if (logger != null)
						logger.log("You stepped on a trap!");
					p.addStatusEffect(StatusEffect.POISONED);
				});
			case 1:
				return new EventCard("Found Gold", p -> {
					if (logger != null)
						logger.log("You found a hidden stash!");
					p.setCoins(p.getCoins() + 5);
				});
			default:
				return new EventCard("Teleport", p -> {
					if (logger != null)
						logger.log("A magical wind blows you away!");
					p.setPosition(RANDOM.nextInt(Constants.BOARD_SIZE));
				});
		}
	}

	public static BossCard createRandomBossCard() {
		return new BossCard(new Enemy("Dragon", 100, 15, 10, 100, 100));
	}
}
