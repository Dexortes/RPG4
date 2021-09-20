package src;

import items.Armor;
import items.Weapon;
import units.*;
import units.CombatUnit;
import units.GeneratorUnits;

import java.util.List;
import java.util.Scanner;

public class RealMain {
    private static boolean gameOn = true;
    private static int menuLvl = 0;
    private static boolean tradeOn = false;
    private static int tradeMenuLvl = 0;
    private static boolean backpackOpen = false;
    private static final Hero hero = new Hero();
    private static final Merchant merch = new Merchant("Боб", 50, 500);
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        hero.setGold(500);
        System.out.println("Привет-привет! Это пробный вариант текстовой RPG." +
                " Если всё же решишься поиграть - помотри что у нас там по пунктам меню.");
        while(gameOn){
            if(menuLvl == 0){               //Main menu
                System.out.println("\nГлавно меню:\n1. Создать героя \n2. Играть \n3. К торговцу \n4. Статистика героя" +
                        "\n5. Рюкзак \n6. Выход");
                int choice = input.nextInt();
                switch (choice){
                    case 1:
                        if(hero.getName() == null || hero.getHealth() <= 0){
                            hero.setHealth(Unit.HEALTH);
                            menuLvl = 1;
                        } else {
                            System.out.println("Герой уже в наличии, рекомендуем" +
                                    " его использовать по прямому назначению.");
                            continue;
                        }
                    case 2:
                        if(hero.getName() == null){
                            System.out.println("Сражаться-то пока некому. Может сначала создадим героя?");
                        } else {
                            battle();
                        }
                        continue;
                    case 3:
                        menuLvl = 2;
                        tradeOn = true;
                        continue;
                    case 4:
                        if(hero.getName() == null){
                            System.out.println("Какого героя?..");
                        } else {
                            System.out.println(hero.getInfoFull());
                        }
                        continue;
                    case 5:
                        System.out.println("Открываем рюкзак");
                        menuLvl = 3;
                        backpackOpen = true;
                        continue;
                    case 6:
                        System.out.println("Удачи!");
                        gameOn = false;
                        break;
                    default:
                        System.out.println("Некорректный ввод.");
                }
            } else if(menuLvl == 1){
                menuLvl1();
            } else if (menuLvl == 2){
                if(hero.getName() == null){
                    System.out.println("Героя бы для начала создать...");
                    menuLvl = 0;
                } else {
                    menuLvl2();
                }
            } else if(menuLvl == 3){
                if(hero.getName() == null){
                    System.out.println("Нет героя - нет рюкзака.");
                    menuLvl = 0;
                } else {
                    menuLvl3();
                }
            }
        }
    }

    private static void battle(){
        GeneratorUnits generatorUnits = new GeneratorUnits(hero);
        List<CombatUnit> listMonster = generatorUnits.generateMonsters();

        //Test-block for high experience
        listMonster.clear();
        listMonster.add(new Skeleton("Скелет1", 100, 200, 200,
                20, 0, 0, 0, 0, 1));
        listMonster.add(new Skeleton("Скелет2", 70, 200, 200,
                40, 0, 0, 0, 0, 1));
        listMonster.add(new Skeleton("Скелет3", 110, 200, 200,
                40, 0, 0, 0, 0, 1));

        //Test-block for weapon and armor
        Armor armor = new Armor("Панцирь бедной черепахи", 25, 75, 1);
        Weapon weapon = new Weapon("Ржавый меч", 30, 100, 1);
        hero.arm(weapon);
        hero.arm(armor);

        Battle battle = new Battle(hero, listMonster);
        battle.action();
    }

    private static void menuLvl1(){             //Hero creation
        Scanner input = new Scanner(System.in);
        System.out.println("Для начала можем выбрать ему имя.");
        String name = input.nextLine();
        hero.setName(name);
        System.out.println("Значит " + hero.getName() + "? Хорошо, а теперь подберём соответствующий тип");
        System.out.println("1. Проворный \n2. Мощный \n3. Везучий");
        int choice = input.nextInt();
        hero.heroType(choice);
        hero.getInfoFull();
        System.out.println("Ну что ж, с гером определились, теперь к игре?");
        menuLvl = 0;
    }

    private static void menuLvl2(){             //trade
        System.out.println("Торговец приветствует вас в своей лавке и предлагает на выбор несколько товаров");
        Trade trade = new Trade(hero, merch);
        while (tradeOn){
            if(tradeMenuLvl == 0){
                System.out.println("Выберите, что вас интересует: \n1. Зелья \n2. Экипировка \n3. Покинуть торговца");
                int tradeChoice = input.nextInt();
                switch (tradeChoice) {
                    case 1 -> tradeMenuLvl = 1;
                    case 2 -> tradeMenuLvl = 2;
                    case 3 -> {
                        menuLvl = 0;
                        tradeOn = false;
                    }
                    default -> System.out.println("Некорректный ввод");
                }
            } else if (tradeMenuLvl == 1){
                System.out.println("1. Малое зелье \n2. Среднее зелье \n3. Большое зелье \n4. Вернуться");
                int choice = input.nextInt();
                if(choice == 4){
                    tradeMenuLvl = 0;
                } else if(choice < 0 || choice > 4){
                    System.out.println("Некорректный ввод");
                } else {
                    trade.getPot(choice - 1);
                }
            } else if (tradeMenuLvl == 2){
                System.out.println("1. Меч \n2. Щит \n3. Топор \n4. Шлем \n5. Доспехи \n6. Вернуться");
                int choice = input.nextInt() - 1;
                if(choice == 5){
                    tradeMenuLvl = 0;
                } else if(choice < 0 || choice > 5){
                    System.out.println("Некорректный ввод");
                } else {
                    trade.getEquip(choice);
                }
            }
        }
    }

    private static void menuLvl3(){ //TODO finish the work with backpack
        while(backpackOpen){
            hero.showBackpack();
            System.out.println("Что пользуем, что надеваем?");
            for(int i = 0; i < Hero.backpackSize(); i++){ //TODO another copy from backpack, rework
                System.out.print((i + 1) + ". ");
                System.out.println(hero.getBackpackItem(i + 1).name);
            }
            System.out.println((Hero.backpackSize() + 1) + ". Выход");
            int choice = input.nextInt();
            if(choice == (Hero.backpackSize() + 1)){
                menuLvl = 0;
                backpackOpen = false;
            } else if(choice > Hero.backpackSize() + 1){
                System.out.println("Некорретный ввод");
            } else {
                hero.useItem(hero.getBackpackItem(choice)); //TODO Rework
            }
        }
    }
}
