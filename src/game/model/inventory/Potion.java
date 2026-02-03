package game.model.inventory;

import game.model.Player;

public class Potion extends InventoryObject {
	private final int healAmount;

	public Potion(String name, String description, double weight, int healAmount) {
		super(name, description, weight);
		this.healAmount = healAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	@Override
	public void use(Player player) {
		System.out.println(player.getName() + " beve " + getName() + ".");
		player.heal(healAmount);
		// Nota: La rimozione dall'inventario deve essere gestita dal controller/manager
		// che ha chiamato questo metodo.
	}
}
