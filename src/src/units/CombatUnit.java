package src.units;

import items.Armor;
import items.Weapon;

abstract public class CombatUnit extends Unit {

    public static final int STRENGTH = 5;
    public static final int AGILITY = 5;
    public static final int LUCK = 5;
    public static final int DAMAGE = 5;
    public static final int DEFENCE = 5;
    public static final int LEVEL = 1;

    protected int strength;
    protected int agility;
    protected int luck;
    protected int damage;
    protected int defence;
    protected int level;
    protected int experience;
    protected int maxHP = health;

    protected Weapon weapon;
    protected Armor armor;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public CombatUnit(String name, int health, int gold, int experience,
                      int power, int agility, int luck, int damage, int defence, int level) {
        super(name, health, gold);
        this.strength = power;
        this.agility = agility;
        this.luck = luck;
        this.damage = damage;
        this.defence = defence;
        this.level = level;
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isAlive(){
        return getHealth() > 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getTotalDamage() {
        int totalDamage = getDamage();
        totalDamage += weapon == null ? 0 : weapon.getPoints();
        return totalDamage;
    }

    public int getTotalDefence() {
        int totalDefence = getDefence();
        totalDefence += armor == null ? 0 : armor.getPoints();
        return totalDefence;
    }

    @Override
    public String toString() {
        return name +
                ", lvl=" + level +
                ", HP: " + health + "/" + maxHP;
    }

    public abstract int attack();

    public abstract int defence();

}
