package src.units;

import items.*;

import java.util.Random;

public class Hero extends CombatUnit{

    public static final int LEVEL = 1;
    protected int kill;//количество убитых
    public static final int BASE_EXPERIENCE = 100;
    protected int maxHP = health;
    protected static Backpack backpack = new Backpack();
    protected int lvlUpThreshold;

    public Hero(){
        super(null, HEALTH, GOLD, EXPERIENCE, STRENGTH, AGILITY, LUCK, DAMAGE, DEFENCE, LEVEL);
        this.kill = 0;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public boolean currentLevelUp(){
        for(int i = 0; i < level; i++){
            lvlUpThreshold = (int)(BASE_EXPERIENCE + 0.75 * BASE_EXPERIENCE * level);
            if(experience < lvlUpThreshold){
                return false;
            }
            experience = experience - lvlUpThreshold;
            level = level + 1;
            setStats(); //power, agi, luck
            System.out.println("Level UP! Exp: " + experience + "/" + lvlUpThreshold);
            getStats();
        } return true;
    }

    private void setStats(){
        agility = (int)(agility + AGILITY * 0.2);
        strength = (int)(strength + STRENGTH * 0.2);
        luck = (int)(luck + LUCK * 0.2);
        health = health + 5 * strength;
        maxHP = maxHP + 5 * strength;
    }

    private void getStats(){
        System.out.println("Текущие статы: \n сила: " + strength + "\n ловкость: " + agility + "\n удача: " + luck +
                "\n здоровье: " + health + "/" + maxHP);
    }

    @Override
    public int attack() {
        int chance = new Random().nextInt(10) + 1;
        damage = DAMAGE + level + 5 + strength;
        if (chance + luck / 5 > 7) {
            return (int) (getTotalDamage() * 1.5);
        } else {
            return getTotalDamage();
        }
    }
    @Override
    public int defence() {
        defence = DEFENCE + level + agility / 5;
        return getTotalDefence();
    }

    @Override
    public String toString() {
        return name + ", lvl: " + level + ", HP: " + health + "/" + maxHP + ", exp: " + experience + "/" + lvlUpThreshold;
    }

    public void heroType(int choice){
        switch (choice) {
            case 1 -> agility += 5;
            case 2 -> strength += 5;
            case 3 -> luck += 10;
            default -> System.out.println("Некорректный ввод");
        }
    }

    //Item methods
    public void showBackpack(){
        backpack.backpackContent();
    }
    public void removeFromBackpack(){
        backpack.deleteNoQuantity();
    }
    public Item getBackpackItem(int num){
        return backpack.getItem(num);
    }
    public static int backpackSize(){
        return backpack.backpackSize();
    }
    public static void putInBackPack(Item item){
        Backpack.putInBackpack(item);
        backpackSize();
    }

    public void useItem(Item item){
        if(item instanceof Pot){
            drinkPot(item);
        } else if (item instanceof Equipment){
            arm(item);
        }
        removeFromBackpack();
    }
    public void drinkPot(Item potion){
        health = health + potion.getPoints();
        if(health > maxHP){
            health = maxHP;
        }
        potion.setQuantity(potion.getQuantity() - 1);
        System.out.println(name + " выпил " + potion.name + ". Текущее здоровье " + health + "/" + maxHP);
    }
    public void arm(Item equip){
        if(equip instanceof Weapon){
            if(getWeapon() != null) {
                putInBackPack(weapon);
                disarm(weapon);
            }
            setWeapon((Weapon)equip);
        } else {
            if(getArmor() != null) {
                putInBackPack(armor);
                disarm(armor);
            }
            setArmor((Armor) equip);
        }
        equip.setQuantity(equip.getQuantity() - 1);
        removeFromBackpack();
    }
    public void disarm(Item equip){
        putInBackPack(equip);
        if(equip instanceof Weapon){
            setWeapon(null);
        } else {
            setArmor(null);
        }
    }

    public String getInfoFull() {
        return "Герой: " + name + ", уровень: " + level + ", опыт: " + experience + "/" + lvlUpThreshold +
                "\n здоровье: " + health + ", золото: " + gold + ", убийств: " + kill +
                "\nСтаты:" +
                "\nСила: " + strength +
                "\nЛовность: " + agility +
                "\nУдача: " + luck +
                "\nОружие: " + weapon + ", Броня: " + armor;
    }
}
