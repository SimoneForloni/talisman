package game.model.inventory;

import game.model.Player;

public class Armor extends InventoryObject {
	private final int defenseBonus;

	public Armor(String name, String description, double weight, int defenseBonus) {
		super(name, description, weight);
		this.defenseBonus = defenseBonus;
	}

	public int getDefenseBonus() {
		return defenseBonus;
	}

	@Override
	public void use(Player player) {
		// Logica placeholder per l'equipaggiamento
		System.out.println(player.getName() + " equipaggia l'armatura: " + getName() +
				" (Difesa +" + defenseBonus + ")");
		// Qui in futuro aggiorneremo lo stato 'equippedArmor' del player
	}
}
