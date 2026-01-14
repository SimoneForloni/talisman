package game.model;

import java.util.ArrayList;
import game.model.inventory.*;

public class Player {

	private int maxHp;
	private int coins;
	private int position;

	private String name;
	private int hp;
	private int xp;
	private int upgradePoints;
	private int strength;
	private int defense;
	private int intelligence;
	private int carisma;
	private int agility;
	private int luck;

	private ArrayList<InventoryObject> inventory;
	private ArrayList<InventoryObject> spells;

	// ===========================================================
	// COSTRUTTORI
	// ===========================================================

	public Player() {
	}

	public Player(String name, String classType) {
		this.name = name;
		this.maxHp = 100;
		this.hp = 100;
		this.xp = 0;
		this.upgradePoints = 0;
		this.coins = 0;
		this.position = 0;
		this.inventory = new ArrayList<>();
		this.spells = new ArrayList<>();

		switch (classType.toLowerCase()) {
			case "warrior":
				this.strength = 4;
				this.defense = 3;
				this.intelligence = 2;
				this.carisma = 2;
				this.agility = 3;
				this.luck = 2;
				break;

			case "wizard":
				this.strength = 2;
				this.defense = 3;
				this.intelligence = 4;
				this.carisma = 2;
				this.agility = 2;
				this.luck = 3;
				break;

			case "thief":
				this.strength = 3;
				this.defense = 2;
				this.intelligence = 2;
				this.carisma = 3;
				this.agility = 4;
				this.luck = 2;
				break;
		}
	}

	// ===========================================================
	// GETTER
	// ===========================================================

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getXp() {
		return xp;
	}

	public int getXpPoint() {
		return upgradePoints;
	}

	public int getStrength() {
		return strength;
	}

	public int getDefense() {
		return defense;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getCarisma() {
		return carisma;
	}

	public int getAgility() {
		return agility;
	}

	public int getLuck() {
		return luck;
	}

	public int getCoins() {
		return coins;
	}

	public int getPosition() {
		return position;
	}

	public ArrayList<InventoryObject> getInventory() {
		return inventory;
	}

	public ArrayList<InventoryObject> getSpells() {
		return spells;
	}

	// ===========================================================
	// SETTER
	// ===========================================================

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public void setUpgradePoints(int upgradePoints) {
		this.upgradePoints = upgradePoints;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public void setCarisma(int carisma) {
		this.carisma = carisma;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	// ===========================================================
	// METODI DI LOGICA / AZIONI
	// ===========================================================



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name: " + name + "\n");
		sb.append("Strenth: " + strength + "\n");
		sb.append("Defense: " + defense + "\n");
		sb.append("Intelligence: " + intelligence + "\n");
		sb.append("Carisma: " + carisma + "\n");
		sb.append("Agility: " + agility + "\n");
		sb.append("Luck: " + luck + "\n");

		return sb.toString();
	}
}