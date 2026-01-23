package game.model;

public interface Combatant {
    String getName();
    int getHp();
    int getAttack();
    int getDefense();
    boolean isAlive();
    void takeDamage(int damage);
}