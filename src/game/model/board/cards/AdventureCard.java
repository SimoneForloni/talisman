package game.model.board.cards;

import game.model.Player;

public interface AdventureCard {
    void execute(Player player);
    String getName();
    CardType getType();
}
