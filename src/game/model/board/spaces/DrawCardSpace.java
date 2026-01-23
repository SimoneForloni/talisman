package game.model.board.spaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.model.Player;
import game.model.board.cards.AdventureCard;
import game.model.board.cards.CardType;
import game.service.factories.AdventureDeck;

public class DrawCardSpace extends Space {

	private static final Random random = new Random();
	private AdventureDeck deck;

	public DrawCardSpace(String name, String description) {
		super(name, description);
		this.deck = new AdventureDeck();
	}

	/**
	 * The `onLand` function draws a specified number of Adventure Cards for a player, separates
	 * enemies from other cards, resolves combat encounters first, and then resolves the effects of the
	 * remaining cards if the player is still alive.
	 * 
	 * @param player The `player` parameter in the `onLand` method represents the player who has landed
	 * on a specific location in the game. This method is responsible for drawing adventure cards for
	 * the player and resolving any encounters or events based on the drawn cards.
	 */
	@Override
	public void onLand(Player player) {
		// Decide internamente quante carte pescare (1 o 2)
		int cardsToDraw = 1 + random.nextInt(3);
		System.out.printf("Drawing %d Adventure Card(s)...\n", cardsToDraw);
		
		List<AdventureCard> drawnCards = new ArrayList<>();
		for (int i = 0; i < cardsToDraw; i++) {
			AdventureCard card = deck.drawCard();
			System.out.println("Drew: " + card.getName());
			drawnCards.add(card);
		}

		// Separa nemici e altre carte
		List<AdventureCard> enemies = drawnCards.stream()
				.filter(c -> c.getType() == CardType.ENEMY)
				.toList();
		
		List<AdventureCard> others = drawnCards.stream()
				.filter(c -> c.getType() != CardType.ENEMY)
				.toList();

		// Risolvi prima i combattimenti
		for (AdventureCard enemyCard : enemies) {
			if (player.isAlive()) {
				enemyCard.execute(player);
			}
		}

		// Se vivo, risolvi il resto
		if (player.isAlive()) {
			for (AdventureCard card : others) {
				card.execute(player);
			}
		}
	}
}