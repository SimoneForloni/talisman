package game.model.inventory;

import game.model.Player;

public class Weapon extends InventoryObject {
	private final int damageValue;

	public Weapon(String name, String description, double weight, int damageValue) {
		super(name, description, weight);
		this.damageValue = damageValue;
	}

	public int getDamageValue() {
		return damageValue;
	}

	@Override
	public void use(Player player) {
		// Logica placeholder per l'equipaggiamento
		System.out.println(player.getName() + " equipaggia l'arma: " + getName() +
				" (Danno +" + damageValue + ")");
		// Qui in futuro aggiorneremo lo stato 'equippedWeapon' del player
	}
}
