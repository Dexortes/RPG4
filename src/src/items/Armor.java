package src.items;

public class Armor extends Equipment{

    public Armor(String name, int points, int price, int quantity) {
        super(name, points, price, quantity);
    }

    @Override
    public String toString() {
        return "Armour{" +
                "defence=" + getPoints() +
                ", name='" + name + '\'' +
                '}';
    }
}
