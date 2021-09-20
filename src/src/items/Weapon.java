package src.items;

public class Weapon extends Equipment {


    public Weapon(String name, int points, int price, int quantity) {
        super(name, points, price, quantity);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' + "; " +
                "damage='" + getPoints() + '\'' +
                '}';
    }
}
