package src.events;

import src.items.Item;
import src.units.Hero;
import src.units.Merchant;

public class Trade {
    private final Hero hero;
    private final Merchant merch;

    public Trade(Hero hero, Merchant merch) {
        this.hero = hero;
        this.merch = merch;
    }


//    public void getPot(int choice){
//        switch (choice) {
//            case 1 -> buyItem(Merchant.Items.POTION25);
//            case 2 -> buyItem(Merchant.Items.POTION50);
//            case 3 -> buyItem(Merchant.Items.POTION100);
//        }
//    }
//
//    public void getEquip(int choice){
//        switch (choice) {
//            case 1 -> buyItem(Merchant.Items.SWORD);
//            case 2 -> buyItem(Merchant.Items.SHIELD);
//            case 3 -> buyItem(Merchant.Items.AXE);
//            case 4 -> buyItem(Merchant.Items.PLATE);
//            case 5 -> buyItem(Merchant.Items.HELM);
//        }
//    }
//
//    private boolean buyItem(Merchant.Items item){
//        if(hero.getGold() >= item.getPrice()){
//            hero.setGold(hero.getGold() - item.getPrice());
//            merch.setGold(merch.getGold() + item.getPrice());
//            Hero.putInBackPack(item);
//            System.out.println(hero.getName() + " покупает " + item.name);
//            return true;
//        } else {
//            System.out.println("Недостаточно золота.");
//            return false;
//        }
//    }

//    public void getEquip(int choice){
//        switch (choice) {
//            case 1 -> buyItem(Merchant.Items.SWORD);
//            case 2 -> buyItem(Merchant.Items.SHIELD);
//            case 3 -> buyItem(Merchant.Items.AXE);
//            case 4 -> buyItem(Merchant.Items.PLATE);
//            case 5 -> buyItem(Merchant.Items.HELM);
//        }
//    }

    public void getPot(int choice){
        buyItem(merch.getPot(choice));
    }

    public void getEquip(int choice){
        buyItem(merch.getEquip(choice));
    }

    private boolean buyItem(Item item){
        if(hero.getGold() >= item.getPrice()){
            hero.setGold(hero.getGold() - item.getPrice());
            merch.setGold(merch.getGold() + item.getPrice());
            Hero.putInBackPack(item);
            System.out.println(hero.getName() + " покупает " + item.name);
            return true;
        } else {
            System.out.println("Недостаточно золота.");
            return false;
        }
    }

}
