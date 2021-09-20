package src.items;

public class Item {
    public final String name;
    private final int points;
    private final int price;
    private int quantity;

    public Item(String name, int points, int price, int quantity) {
        this.name = name;
        this.points = points;
        this.price = price;
        this.quantity = quantity;
    }
    public int getPoints() {
        return points;
    }
    public int getPrice() {
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String itemInfo(){
        return name + "(на " + points + "): " + quantity;
    }
}
