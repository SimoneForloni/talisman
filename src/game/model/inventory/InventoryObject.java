package game.model.inventory;

import game.model.Player;
import java.util.UUID;

/**
 * Classe astratta che rappresenta un oggetto generico nell'inventario.
 * Contiene attributi comuni a tutti gli oggetti.
 */
public abstract class InventoryObject {
	private final String id;
	private final String name;
	private final String description;
	private final double weight;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================

	public InventoryObject(String name, String description, double weight) {
		this.id = UUID.randomUUID().toString(); // Genera un ID univoco
		this.name = name;
		this.description = description;
		this.weight = weight;
	}

	// ===========================================================
	// GETTER
	// ===========================================================

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return weight;
	}

	// ===========================================================
	// SETTER
	// ===========================================================
	// Nota: Gli attributi sono final per l'immutabilità di base dell'oggetto.
	// Se necessario, rimuovere 'final' e aggiungere i setter.

	// ===========================================================
	// METODI
	// ===========================================================

	/**
	 * Metodo astratto per definire l'azione dell'oggetto quando viene usato.
	 * L'implementazione specifica dipenderà dalla sottoclasse.
	 * 
	 * @param player Il giocatore che usa l'oggetto.
	 */
	public abstract void use(Player player);

	@Override
	public String toString() {
		return String.format("%s - %s (%f g)", getName(), getDescription(), getWeight());
	}
}