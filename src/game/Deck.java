package game;

import java.util.ArrayList;
import java.util.Collections;

import game.service.factories.CardFactory;
import game.service.loggers.GameLogger;
import game.model.board.cards.AdventureCard;
import game.util.Constants;

public class Deck {
	private ArrayList<AdventureCard> deck;
	private final GameLogger logger;
	private int index;

	public Deck(GameLogger logger) {
		this.logger = logger;
		this.deck = new ArrayList<>();
		initializeDeck();
		index = 0;
	}

	private void initializeDeck() {
		deck.clear();
		for (int i = 0; i < Constants.DECK_LENGTH; i++) {
			deck.add(CardFactory.createRandomAdventureCard(logger));
		}
		Collections.shuffle(deck);
	}

	public AdventureCard draw() {
		if (index >= deck.size()) {
			logger.log("Deck reshuffled!");
			Collections.shuffle(deck);
			index = 0;
		}
		return deck.get(index++);
	}
}