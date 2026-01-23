package game.service.managers;

import java.util.Random;
import game.model.Combatant;
import game.model.Enemy;
import game.model.Player;
import game.model.StatusEffect;
import game.util.Methods;

public class CombatManager {

    private static final Random random = new Random();

    public void startBattle(Player player, Enemy enemy) {
        System.out.println("!!! COMBAT START !!!");
        System.out.printf("%s challenges %s!\n\n", player.getName(), enemy.getName());
        Methods.pressEnterToContinue();

        Combatant attacker = player;
        Combatant defender = enemy;

        while (player.isAlive() && enemy.isAlive()) {
            executeTurn(attacker, defender);

            if (!defender.isAlive()) {
                break;
            }

            Combatant temp = attacker;
            attacker = defender;
            defender = temp;
        }

        handleBattleEnd(player, enemy);
    }

    private void executeTurn(Combatant attacker, Combatant defender) {
        if (attacker instanceof Player) {
            applyStatusEffects((Player) attacker);
            if (!attacker.isAlive()) return;
        }

        System.out.printf("--- %s's Turn ---\n", attacker.getName());

        int diceRoll = random.nextInt(6) + 1;
        int totalAttack = attacker.getAttack() + diceRoll;
        int damage = Math.max(0, totalAttack - defender.getDefense());

        System.out.printf("%s attacks with %d (Attack) + %d (Dice) = %d\n",
                attacker.getName(), attacker.getAttack(), diceRoll, totalAttack);

        if (damage > 0) {
            defender.takeDamage(damage);
            System.out.printf("%s defends %d and takes %d damage!\n", defender.getName(), defender.getDefense(), damage);
        } else {
            System.out.printf("%s's defense holds! No damage taken.\n", defender.getName());
        }

        System.out.printf("Remaining HP -> %s: %d | %s: %d\n\n",
                attacker.getName(), attacker.getHp(),
                defender.getName(), defender.getHp());

        Methods.pressEnterToContinue();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Methods.clearScreen();
    }

    private void handleBattleEnd(Player player, Enemy enemy) {
        if (player.isAlive()) {
            System.out.printf("VICTORY! %s has been defeated.\n", enemy.getName());
            System.out.printf("You gained %d XP and %d Coins.\n", enemy.getXpReward(), enemy.getCoinsReward());
            player.setXp(player.getXp() + enemy.getXpReward());
            player.setCoins(player.getCoins() + enemy.getCoinsReward());
        } else {
            System.out.printf("DEFEAT... %s has defeated you.\n", enemy.getName());
        }
    }

    private void applyStatusEffects(Player player) {
        if (player.getStatusEffects().contains(StatusEffect.POISONED)) {
            System.out.println("Poison deals 1 damage!");
            player.takeDamage(1);
        }
        // Altri effetti possono essere gestiti qui
    }
}