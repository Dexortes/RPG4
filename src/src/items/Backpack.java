package src.items;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    protected static List<Item> backpack = new ArrayList<>();

    public static void putInBackpack(Item item){
        if(backpack.size() == 0){
            backpack.add(item);
        }else{
            int itemTypeCounter = 0;
            for(int i = 0; i < backpack.size(); i++) {
                if(item.equals(backpack.get(i))){
                    backpack.get(i).setQuantity(backpack.get(i).getQuantity() + 1);
                } else {
                    itemTypeCounter++;
                    if(itemTypeCounter == backpack.size()){
                        backpack.add(item);
                        break;
                    }
                }
            }
        }
    }

    public Item getItem(int num){
        return backpack.get(num - 1);
    }
    public void backpackContent(){
        System.out.println("В рюкзаке:");
        for (Item item : backpack) {
            System.out.println(item.itemInfo());
        }
    }
    public int backpackSize(){
        return backpack.size();
    }
    public void deleteNoQuantity(){
        for(int i = 0; i < backpack.size(); i++){
            if(backpack.get(i).getQuantity() == 0){
                backpack.remove(i);
            }
        }
    }
}
