package src.units;

import java.util.Random;

abstract public class Monster extends CombatUnit {
    public Monster(String name, int health, int gold, int experience, int power, int agility,
                   int luck, int damage, int defence, int level) {
        super(name, health, gold, experience, power, agility, luck, damage, defence, level);
    }

    @Override
    public int attack() {
        int chance = new Random().nextInt(10) + 1;
        damage = DAMAGE + level + 5 + strength;
        if (chance + luck / 5 > 8) {
            return (int)(damage * 1.5);
        } else {
            return damage;
        }

    }

    @Override
    public int defence() {
        defence = DEFENCE + level + agility / 5;
        return defence;
    }

}
