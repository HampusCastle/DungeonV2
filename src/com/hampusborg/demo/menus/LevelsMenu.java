package com.hampusborg.demo.menus;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.database.repository.FightDao;
import com.hampusborg.demo.database.repository.HeroDao;
import com.hampusborg.demo.database.repository.HistoryDao;
import com.hampusborg.demo.database.repository.MonsterDao;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.monsters.AMonster;
import com.hampusborg.demo.monsters.MonsterFactory;
import com.hampusborg.demo.shop.Shop;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static java.lang.System.exit;

public class LevelsMenu implements IColors {
    Game game;
    Shop shop = new Shop();
    private MonsterFactory monsterFactory;

    Hero hero;
    AMonster monster;

    private final HeroDao heroDao;
    private final DatabaseConnector db;
    private final FightDao fightDao;
    private final MonsterDao monsterDao;
    private Fight currentFight;
    private HistoryDao historyDao;


    public LevelsMenu(Hero hero) {
        this.monsterFactory = new MonsterFactory();
        this.heroDao = new HeroDao();
        this.fightDao = new FightDao();
        this.hero = hero;
        this.db = new DatabaseConnector();
        this.db.openConnection();
        this.game = new Game();
        this.monster = monsterFactory.spawnMonster();
        this.monsterDao = new MonsterDao();
        this.historyDao = new HistoryDao(this.db);


    }

    public void startCombat() throws SQLException {

        System.out.println(GREEN + "Do you want to look at historic fights from your player?\n 1. - Yes! \n 2. - No? lol.. " + RESET);
        int choice = Input.getIntInput("");
        switch (choice) {
            case 1 -> historyDao.fightHistory(hero);

            case 2 -> System.out.println("Well, suit yourself. Be on your way now.");
            default -> System.out.println(RED + "Not listening to instructions is also a choice i guess." + RESET);
        }

        try {
            while (true) {
                AMonster monster = saveSpawnedMonster();

                if (monster != null) {

                    currentFight = new Fight(hero, monster);
                    currentFight.setHeroID(hero.getHeroID());
                    currentFight.setMonsterID(monster.getMonsterID());
                    currentFight.setMonsterInitialHealth(monster.getInitialHealth());

                    System.out.println( YELLOW + "A odd looking creature " + monster.getName() + " seems to be walking towards you.." + RESET);
                    displayMonsterInfo(monster);


                    while (hero.isAlive() && monster.isAlive()) {
                        playerTurn(monster);
                        if (monster.isAlive()) {
                            monsterTurn(monster);
                        }

                    }
                    displayCombatResult(monster);
                    currentFight.setMonsterHealthLost(monster.getHealthLost());

                    saveFightHistory(currentFight, hero.getHeroID(), monster);
                    savePlayer();

                    showAfterFightMenu(monster);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void saveFightHistory(Fight fight, long heroID, AMonster monster) {
        if (fight != null && monster != null && hero != null) {
            fight.setHeroID(heroID);
            fight.setMonsterID(monster.getMonsterID());
            fight.setTimestamp(new Date());

            fight.setMonsterHealthLost(monster.getHealthLost());
            fight.setHeroHealthLost(hero.getInitialHealth() - hero.getHealth());

            if (hero.isAlive()) {
                fight.setWinner("Hero");
            }

            int damageDealt = fight.getDamageDealt();
            fight.setMonsterHealthLost(damageDealt);

            boolean saved = fightDao.saveFightToDatabase(fight, hero, monster, this.db);


            if (saved) {
                hero.setInitialHealth(hero.getHealth());
                monster.setInitialHealth(monster.getHealth());
            } else {
                System.out.println("Failed to save fight to the database");
            }
        } else {
            System.out.println("Fight object is null, cannot save to the database");
        }

    }

    private void playerTurn(AMonster monster) throws SQLException {
        System.out.println( GREEN + "1. - Attack\n 2. - Super awesome and cool monsterinfo \n 3. - Show abilities(and of course sneak peak on your opponent).\n 4. - Try to escape..\n" + "5. -Save your hero" + RESET);

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> playerAttack(monster);
            case 2 -> {
                getMonsterLore(monster);
                playerTurn(monster);
            }
            case 3 -> {
                displayCombatStatus(monster);
                playerTurn(monster);

            }
            case 4 -> {
                System.out.println(YELLOW + "Now he's trying to leave, oh my god." + RESET);
                tryToFlee(monster);
                startCombat();
            }
            case 5 -> {
                savePlayer();
                playerTurn(monster);
            }
            default -> {
                System.out.println(RED + "Please at least try to pay attention.. Give it another go champ, you probably understand basic instructions.." + RESET);
                playerTurn(monster);
            }

        }
    }

    private void playerAttack(AMonster monster) {

        if (currentFight != null) {
            int damage = hero.getDamage();
            hero.attack();
            monster.takeDamage(damage);
            System.out.println(YELLOW + "A half hearted attack dealt " + damage + " damage to " + monster.getName() + " !" + RESET);

            currentFight.setMonsterHealthLost(damage);
            currentFight.setDamageDealt(damage);
        } else {
            System.out.println("Error : fight instance is null!");
        }
    }

    private void monsterTurn(AMonster monster) {
        int damage = monster.getDamage();
        monster.attack();
        hero.takeDamage(damage);
        System.out.println( YELLOW + "The " + monster.getName() + " dealt " + damage + " damage to your lethargic bottom" + RESET);
    }


    private void displayMonsterInfo(AMonster monster) {
        System.out.println(BLUE + "Monster info: \n" + monster.getStatus() + RESET);
    }

    public void displayCombatStatus(AMonster monster) {
        System.out.println(BLUE + "Player info: \n" + hero.getStatus() + RESET);
        System.out.println(BLUE + "Monster info: \n" + monster.getStatus() + RESET);
    }

    private void getMonsterLore(AMonster monster) {
        System.out.println(BLUE + "Spectacular lore coming up: \n" + monster.getLore() + RESET);
    }

    private void displayCombatResult(AMonster monster) {
        if (hero.isAlive()) {
            System.out.println( GREEN + "Wow, you didn't die. " + monster.getName() + " did though, lmao." + RESET);
            hero.loot(monster.getExperience(), monster.getGold());
            System.out.println(YELLOW + "You now have: " + hero.getGold() + " gold, as well as: " + hero.getExperience() + " experience!" + RESET);
        } else {
            System.out.println(RED + "You were defeated, how embarrassing, you could try again from the start, but it's okay to admit defeat." + RESET);
            currentFight.setWinner("Monster");
            exit(0);
        }
    }

    public void showAfterFightMenu(AMonster monster) throws SQLException {
        System.out.println(GREEN +"Welcome to the awesome after fight menu, it looks cool but it's the same as the other ones basically." + RESET);
        System.out.println(GREEN + "1. - Attack next monster\n 2. - Go to the shop\n 3. - Quit out." + RESET);

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> {
                monsterFactory.spawnMonster();
                startCombat();
            }

            case 2 -> {
                System.out.println(YELLOW + "Going shopping, sure.." + RESET);
                shop.buyItems(hero, monster);
            }

            case 3 -> {
                System.out.println(RED + "Wouldn't expect you to comeback, but if you're thinking about it, don't. This is not something that gets better the second time you try it." + RESET);
                System.out.println(RED + "But if you do, your hero is saved." + RESET);
                savePlayer();
                game.exitGame(hero);
            }
            default -> {
                System.out.println(RED + "Hey! Sausage fingers - pay attention and try again." + RESET);
                showAfterFightMenu(monster);
            }
        }
    }

    public void tryToFlee(AMonster monster) {
        if (hero.didFlee()) {
            System.out.println(YELLOW + "You chickened out. " + monster.getName() + " looks confused.." + RESET);
        } else {
            System.out.println(RED + "Feel like I should give you a penalty for that failed escape, well I'll remove 10 health, seems fair right?" + RESET);
            hero.takeDamage(10);
            System.out.println(GREEN + "Your new health: " + hero.getHealth() + RESET);
        }

    }

    public void savePlayer() {
        this.db.openConnection();
        heroDao.saveHeroToDatabase(this.hero, this.db);
    }

    public void loadPlayer() {
        try {
            db.openConnection();

            List<Hero> heroList = heroDao.showHeroes();
            if (heroList.isEmpty()) {
                System.out.println( RED + "No saved players were found." + RESET);
            } else {
                System.out.println(GREEN +"List of saved players: " + RESET);
                for (Hero savedHero : heroList) {
                    System.out.println(GREEN + "Hero id: " + savedHero.getHeroID() + " Hero: " + savedHero.getName() + " Level: " + savedHero.getLevel() + RESET);
                }

                System.out.println(GREEN + "Enter ID of the player you want to load: " + RESET);
                long input = Input.inputInt();

                hero = findHeroInList(input, heroList);

                if (hero != null) {
                    hero.getHealthLost();
                    hero.calculateHealthLost();
                    hero.setInitialHealth(hero.getHealth());
                    if (hero.getHealth() <= 0) {
                        hero.setHealth(1);
                    }
                    System.out.println(GREEN +"Hero loaded: " + hero.getName() +
                            " Level: " + hero.getLevel() +
                            " Health: " + hero.getHealth() + RESET);

                    startCombat();

                } else {
                    System.out.println(RED + "Invalid ID, please try again." + RESET);
                    loadPlayer();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Hero findHeroInList(long id, List<Hero> herolist) {
        for (Hero savedHero : herolist) {
            if (savedHero.getHeroID() == id) {
                return savedHero;
            }
        }
        return null;
    }

    public AMonster saveSpawnedMonster() throws SQLException {
        AMonster monster = monsterFactory.spawnMonster();
        Long monsterID = monsterDao.saveMonsterToDatabase(monster, db);
        if (monsterID != null) {
            monster.setMonsterID(monsterID);
            return monster;
        } else {
            System.out.println("Error: Monster ID is Null");
            return null;
        }
    }
}


