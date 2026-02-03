package game.model.inventory;

import game.model.Player;

/**
 * Rappresenta un incantesimo che un giocatore può lanciare.
 * Gestito separatamente dall'inventario fisico.
 */
public class Spell {

	private final String name;
	private final String description;
	private final int manaCost;
	private final int magicDamage;

	public Spell(String name, String description, int manaCost, int magicDamage) {
		this.name = name;
		this.description = description;
		this.manaCost = manaCost;
		this.magicDamage = magicDamage;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getMagicDamage() {
		return magicDamage;
	}

	/**
	 * Esegue l'incantesimo.
	 * 
	 * @param caster Il giocatore che lancia l'incantesimo.
	 */
	public void cast(Player caster) {
		// In futuro qui si potrà controllare il mana del giocatore
		System.out.println(caster.getName() + " lancia " + name +
				" (Danno: " + magicDamage + ", Costo: " + manaCost + " Mana)!");
	}
}
