package src;

import units.CombatUnit;
import units.Hero;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    private final Hero hero;
    private final List<CombatUnit> monsters;
    private final Random random = new Random(47);
    private int counter = 0;
    private final Scanner input = new Scanner(System.in);
    private boolean backpackOpen = false;

    public Battle(Hero hero, List<CombatUnit> monsters) {
        this.hero = hero;
        this.monsters = monsters;
    }

    public void action() {
        while (monsters.size() != 0 && hero.getHealth() > 0) {
            if (counter % 2 == 0) { //TODO make "turn()" method for unloading action()
                System.out.println("Что делаем?");
                for(int i = 0; i < monsters.size(); i++){
                    System.out.println((i + 1) + ". Атака: " + monsters.get(i).getName());
                }
                System.out.println((monsters.size() + 1) + ". Сплэш-атака!");
                System.out.println((monsters.size() + 2) + ". Залезть в рюкзак.");
                int choice = input.nextInt();
                if(choice > monsters.size() + 2 || choice < 1){ //TODO make an exception for chars and words
                    System.out.println("Неверный выбор.");
                    continue;
                }
                if(choice == monsters.size() + 1){
                    System.out.println(hero.getName() + " наносит массивынй удар!");
                    splash(hero, monsters);
                }
                else if (choice == monsters.size() + 2){ //TODO make an appropriate condition
                    inventory();
                }
                else {
                    strike(hero, monsters.get(choice - 1));
                }

            } else {
                strike(monsters, hero);
            }
            counter++;
            kills();
            if(hero.currentLevelUp()){
                System.out.println("Уровень героя " + hero.getName() + " повышен!");
            }
        }
        if(hero.getHealth() <= 0){
            System.out.println("Поражение!");
            hero.setName(null);
        } else if (monsters.size() == 0){
            System.out.println("Победа!");
        }
        hero.isAlive();
    }

    private void kills(){
        for(int i = 0; i < monsters.size(); i++){
            if(monsters.get(i).getHealth() <= 0){
                hero.setExperience(hero.getExperience() + monsters.get(i).getExperience());
                hero.setGold(hero.getGold() + monsters.get(i).getGold());
                monsters.remove(i);
                hero.setKill(hero.getKill() + 1);
            }
        }
    }

    public void inventory(){
        backpackOpen = true;
        while(backpackOpen){
            hero.showBackpack();
            System.out.println("Что пользуем, что надеваем?");
            for(int i = 0; i < Hero.backpackSize(); i++){
                System.out.print((i + 1) + ". ");
                System.out.println(hero.getBackpackItem(i + 1).name);
            }
            System.out.println((Hero.backpackSize() + 1) + ". Выход");
            int choice = input.nextInt();
            if(choice == (Hero.backpackSize() + 1)){
                backpackOpen = false;
            } else if(choice > Hero.backpackSize() + 1){
                System.out.println("Некорретный ввод");
            } else {
                hero.useItem(hero.getBackpackItem(choice));
            }
        }
    }

    //Attack methods
    private void strike(List<CombatUnit> attackers, CombatUnit defender) {
        for (CombatUnit attacker : attackers) {
            strike(attacker, defender);
        }
    }
    private void strike(CombatUnit attacker, CombatUnit defender) {
        int damage = (attacker.attack() - defender.defence());
        if(damage <= 0){
            damage = 0;
            System.out.println(attacker + " не пробил броню");
        } else if (damage <= defender.getHealth()) {
            defender.setHealth(defender.getHealth() - damage);
            System.out.println(attacker + " ударил на " + damage + " урона " + defender);
        } else {
            defender.setHealth(0);
            System.out.println(attacker + " ударил на " + damage + " урона " + defender + ". " + defender + " погибает.");
        }
    }
    public void splash(Hero hero, List<CombatUnit> combatUnits) {
        for (CombatUnit combatUnit : combatUnits) {
            strike(hero, combatUnit);
        }
    }
}