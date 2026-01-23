package game.model.board.cards;

import game.model.Player;
import game.model.inventory.InventoryObject;

public class ItemCard implements AdventureCard {
    private InventoryObject item;
    private String name;

    public ItemCard(String name, InventoryObject item) {
        this.name = name;
        this.item = item;
    }

    @Override
    public void execute(Player player) {
        System.out.printf("You found an item: %s!\n", name);
        player.getInventory().add(item);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CardType getType() {
        return CardType.ITEM;
    }
}
