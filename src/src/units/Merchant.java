package src.units;

import items.*;

import java.util.List;

public class Merchant extends Unit {
    public Merchant(String name, int health, int gold) {
        super(name, health, gold);
    }

    public static final List<Pot> pots = List.of(
            new Pot("Малое зелье исцеления", 25, 25, 1),
            new Pot("Среднее зелье исцеления", 50, 50, 1),
            new Pot("Большое зелье исцеления", 100, 100,1 )
    );

    public static final List<Equipment> equips = List.of(
            new Weapon("Рыцарский меч", 12, 150, 1),
            new Armor("Прочный щит", 15, 300, 1),
            new Weapon("Варварский топор", 15, 200, 1),
            new Armor("Лёгкий шлем", 5, 80, 1),
            new Armor("Крепкий доспех", 20, 650, 1)
    );

    public Item getPot(int num){
        return pots.get(num);
    }

    public Item getEquip(int num){
        return equips.get(num);
    }

    public String showPrice(Item item){
        return item.name + " на " + item.getPoints() + ": " + item.getPrice() + "г";
    }

    @Override
    public String toString() {
        return name + ", HP = " + health + ", gold = " + gold;
    }
}
