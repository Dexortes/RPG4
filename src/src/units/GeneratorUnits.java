package src.units;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorUnits {
    private final Hero hero;

    public GeneratorUnits(Hero hero) {
        this.hero = hero;
    }

    private final Random random = new Random();

    public List<CombatUnit> generateMonsters() {

        List<CombatUnit> poll = new ArrayList<>();
        List<Integer> levelsList = getMonsterLevel();
        for (Integer integer : levelsList) {
            int count = random.nextInt(3);
            switch (count) {
                case 0 -> {
                    CombatUnit skeleton = new Skeleton("Скелет", 0, 50, 50,
                            0, 0, 0, 0, 0, integer);
                    setMonsterStats(skeleton);
                    setMonsterName(skeleton);
                    poll.add(skeleton);
                }
                case 1 -> {
                    CombatUnit zombie = new Zombie("Зомби", 0, 50, 50,
                            0, 0, 0, 0, 0, integer);
                    setMonsterStats(zombie);
                    setMonsterName(zombie);
                    poll.add(zombie);
                }
                case 2 -> {
                    CombatUnit ogre = new Ogre("Огр", 0, 50, 50,
                            0, 0, 0, 0, 0, integer);
                    setMonsterStats(ogre);
                    setMonsterName(ogre);
                    poll.add(ogre);
                }
            }
        }
        return poll;
    }

    public List<Integer> getMonsterLevel() {
        int enemyNum = hero.level;
        List<Integer> enemiesLvl = new ArrayList<>();
        while (enemyNum != 0) {
            int enemyLvl = random.nextInt(enemyNum) + 1;
            enemyNum -= enemyLvl;
            enemiesLvl.add(enemyLvl);
        }
        return enemiesLvl;
    }

    private void setMonsterStats(CombatUnit monster) {
        if (monster instanceof Skeleton) {
            monster.agility = CombatUnit.AGILITY + monster.level + 2;
            monster.strength = CombatUnit.STRENGTH + monster.level;
        } else {
            monster.agility = CombatUnit.AGILITY + monster.level;
            monster.strength = CombatUnit.STRENGTH + monster.level + 2;
        }
        monster.experience = (Unit.EXPERIENCE  + 50 * monster.level);
        monster.gold = (int)(Unit.GOLD  + 50 * monster.level * 0.5);
        monster.luck = CombatUnit.LUCK + monster.level;
        monster.health = CombatUnit.HEALTH + monster.level * monster.strength;
    }


    private void setMonsterName(CombatUnit monster) {
        String name;

        Random random = new Random();

        List<String> poolRarity = List.of("Обычный", "Редкий", "Эпический", "Легендарный", "Ультра");
        List<String> poolPower = List.of("здоровый", "крепкий", "могучий", "могущественный", "мощный");
        List<String> poolAgility = List.of("ловкий", "поворотливый", "искусный", "проворный", "расторопный");

        int rarity = random.nextInt(5);
        int stat = random.nextInt(5);
        if (monster.strength > monster.agility) {
            name = String.format("%s %s", poolRarity.get(rarity), poolPower.get(stat));
        } else {
            name = String.format("%s %s", poolRarity.get(rarity), poolAgility.get(stat));
        }
        monster.name = name + " " + monster.name;
    }
}
