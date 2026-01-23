package game.model.board.cards;

import java.util.function.Consumer;
import game.model.Player;

public class EventCard implements AdventureCard {
    private String name;
    private Consumer<Player> effect;

    public EventCard(String name, Consumer<Player> effect) {
        this.name = name;
        this.effect = effect;
    }

    @Override
    public void execute(Player player) {
        System.out.printf("Event: %s\n", name);
        effect.accept(player);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CardType getType() {
        return CardType.EVENT;
    }
}