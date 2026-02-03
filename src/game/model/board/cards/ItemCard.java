package game.model.board.cards;

import game.model.Player;
import game.model.inventory.InventoryObject;

/**
 * Rappresenta una carta che contiene un oggetto.
 * Quando il giocatore incontra questa carta, l'oggetto viene aggiunto al suo
 * inventario.
 */
public class ItemCard implements AdventureCard {

	private final InventoryObject item;

	public ItemCard(InventoryObject item) {
		this.item = item;
	}

	public InventoryObject getItem() {
		return item;
	}

	@Override
	public void execute(Player player) {
		System.out.println("Hai trovato un oggetto: " + item.getName());
		player.addItem(item);
	}

	@Override
	public String getName() {
		return item.getName();
	}

	@Override
	public CardType getType() {
		return CardType.ITEM;
	}
}
