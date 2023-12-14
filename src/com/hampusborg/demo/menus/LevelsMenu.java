package com.hampusborg.demo.menus;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.database.repository.HeroDao;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.monsters.AMonster;
import com.hampusborg.demo.monsters.MonsterFactory;
import com.hampusborg.demo.shop.Shop;

import java.util.List;

import static java.lang.System.exit;

public class LevelsMenu implements IColors {
    Game game;
    Shop shop = new Shop();
    private MonsterFactory monsterFactory;

    Hero hero;

    HeroDao heroDao;
    DatabaseConnector db;

    public LevelsMenu(Hero hero) {
        this.monsterFactory = new MonsterFactory();
        this.heroDao = new HeroDao();
        this.hero = hero;
        this.db = new DatabaseConnector();
        this.game = new Game();


    }

    public void startCombat() {
        db.openConnection();
        AMonster monster = monsterFactory.spawnMonster();
        System.out.println(YELLOW + "A odd looking creature " + monster.getName() + " seems to be walking towards you.." + RESET);
        displayMonsterInfo(monster);
        while (hero.isAlive() && monster.isAlive()) {
            playerTurn(monster);
            if (monster.isAlive()) {
                monsterTurn(monster);
            }
        }
        displayCombatResult(monster);
        showAfterFightMenu(monster);
    }

    private void playerTurn(AMonster monster) {
        System.out.println(RED + "1. - Attack\n 2. - Super awsome and cool monsterinfo \n 3. - Show abilities(and of course sneak peak on your opponent).\n 4. - Try to escape..\n" + "5. -Save your hero" + RESET);

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
                ;
            }
            case 4 -> {
                System.out.println(CYAN + "Now he's trying to leave, oh my god." + RESET);
                tryToFlee( monster);
                startCombat();
            }
            case 5 -> savePlayer();
            default -> {
                System.out.println(PURPLE + "Please at least try to pay attention.. Give it another go champ, you probably understand basic instructions.." + RESET);
                playerTurn(monster);
            }

        }
    }

    private void playerAttack(AMonster monster) {
        int damage = hero.getDamage();
        hero.attack();
        monster.takeDamage(damage);
        System.out.println(BLUE + "A half hearted attack dealt " + damage + " damage to " + monster.getName() + " !" + RESET);
    }

    private void monsterTurn(AMonster monster) {
        int damage = monster.getDamage();
        monster.attack();
        hero.takeDamage(damage);
        System.out.println(YELLOW + "The " + monster.getName() + " dealt " + damage + " damage to your lethargic bottom" + RESET);
    }


    private void displayMonsterInfo(AMonster monster) {
        System.out.println(CYAN + "Monster info: " + RESET);
        System.out.println(monster.getStatus());
    }

    public void displayCombatStatus(AMonster monster) {
        System.out.println(RED + "Player info: \n" + hero.getStatus() + RESET);
        System.out.println(BLUE + "Monster info: \n" + monster.getStatus() + RESET);
    }

    private void getMonsterLore(AMonster monster) {
        System.out.println(RED + "Spectacular lore coming up: \n" + monster.getLore() + RESET);
    }

    private void displayCombatResult(AMonster monster) {
        if (hero.isAlive()) {
            System.out.println(CYAN + "Wow, you didn't die. " + monster.getName() + " did though, lmao." + RESET);
            hero.loot(monster.getExperience(), monster.getGold());
            System.out.println(YELLOW + "You now have: " + hero.getGold() + " gold, as well as: " + hero.getExperience() + " experience!" + RESET);
        } else {
            System.out.println(GREEN + "You were defeated, how embarrassing, you could try again from the start, but it's okay to admit defeat." + RESET);
            exit(0);
        }
    }

    public void showAfterFightMenu(AMonster monster) {
        System.out.println(RED + "Welcome to the awesome after fight menu, it looks cool but it's the same as the other ones basically." + RESET);
        System.out.println(GREEN + "1. - Attack next monster\n 2. - Go to the shop\n 3. - Quit out." + RESET);

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> {
                startCombat();
            }

            case 2 -> {
                System.out.println(YELLOW + "Going shopping, sure.." + RESET);
                shop.buyItems(hero, monster);
            }

            case 3 -> {
                game.exitGame(hero);
                System.out.println(BLUE + "Wouldn't expect you to comeback, but if you're thinking about it, don't. This is not something that gets better the second time you try it." + RESET);
            }
            default -> {
                System.out.println(GREEN + "Hey! Sausage fingers - pay attention and try again." + RESET);
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
        heroDao.saveHeroToDatabase(hero, this.db);
        System.out.println("Game saved!");
    }

    public void loadPlayer() {
        db.openConnection();
        System.out.println("Input ID to choose player to load!");
        long input = (long) Input.inputInt();
        this.hero.setHero(heroDao.findHeroId(input, this.db, hero));
    }

    public void choiceOfSavedPlayers() {
        List<Hero> heroList = heroDao.showHeroes(hero, this.db);
        for (Hero hero : heroList)
            if (hero != null) {
                System.out.println("Player id: " + hero.getHeroID() + "Hero: " + hero.getName() + "Class: " + hero.getClass() + "Level: " + hero.getLevel() + "\n");
            }
    }
}
