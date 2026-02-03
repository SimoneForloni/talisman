package game.service.factories;

import game.model.inventory.Armor;
import game.model.inventory.InventoryObject;
import game.model.inventory.Potion;
import game.model.inventory.Weapon;
import java.util.Random;

/**
 * Factory per generare istanze casuali di oggetti di inventario.
 * Utile per popolare i mazzi di carte o i tesori.
 */
public class ItemFactory {
    private static final Random RANDOM = new Random();

    public static InventoryObject createRandomItem() {
        int roll = RANDOM.nextInt(100);
        if (roll < 40) return createRandomPotion(); // 40% Pozione
        if (roll < 70) return createRandomWeapon(); // 30% Arma
        return createRandomArmor();                 // 30% Armatura
    }

    public static Weapon createRandomWeapon() {
        String[] names = {"Spada Corta", "Ascia da Guerra", "Lancia", "Pugnale"};
        String name = names[RANDOM.nextInt(names.length)];
        int dmg = 1 + RANDOM.nextInt(4); // Danno 1-4
        return new Weapon(name, "Un'arma semplice ma efficace.", 2.5, dmg);
    }

    public static Armor createRandomArmor() {
        String[] names = {"Elmo di Ferro", "Scudo di Legno", "Corazza di Cuoio"};
        String name = names[RANDOM.nextInt(names.length)];
        int def = 1 + RANDOM.nextInt(3); // Difesa 1-3
        return new Armor(name, "Protegge dagli attacchi nemici.", 5.0, def);
    }

    public static Potion createRandomPotion() {
        int heal = 5 + RANDOM.nextInt(10); // Cura 5-14
        return new Potion("Pozione Curativa", "Ripristina punti vita.", 0.5, heal);
    }
    
    // Metodo per creare un oggetto specifico se necessario
    public static Weapon createExcalibur() {
        return new Weapon("Excalibur", "La spada leggendaria.", 3.0, 10);
    }
}
