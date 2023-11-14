package com.hampusborg.demo.menus;

import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.monsters.AMonster;
import com.hampusborg.demo.monsters.MonsterFactory;
import com.hampusborg.demo.shop.Shop;

public class LevelsMenu {
    Game game = new Game();
    Shop shop = new Shop();
    private MonsterFactory monsterFactory;

    public LevelsMenu() {
        this.monsterFactory = new MonsterFactory();
    }

    public void startCombat(AHero hero) {
        AMonster monster = monsterFactory.spawnMonster();
        System.out.println("A odd looking creature " + monster.getName() + " seems to be walking towards you..");
        displayMonsterInfo(monster);
        while (hero.isAlive() && monster.isAlive()) {
            playerTurn(hero, monster);
            if (monster.isAlive()) {
                monsterTurn(hero, monster);
            }
        }
        displayCombatResult(hero, monster);
        showAfterFightMenu(hero, monster);
    }

    private void playerTurn(AHero player, AMonster monster) {
        System.out.println("1. - Attack\n 2. - Super awsome and cool monsterinfo \n 3. - Show abilities(and of course sneak peak on your opponent).\n 4. - Try to escape..");

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> playerAttack(player, monster);
            case 2 -> {
                getMonsterLore(monster);
                playerTurn(player, monster);
            }
            case 3 -> {
                displayCombatStatus(player, monster);
                playerTurn(player, monster);;
            }
            case 4 -> {
                System.out.println("Now he's trying to leave, oh my god.");
                tryToFlee(player, monster);
                startCombat(player);
            }
            default -> {
                System.out.println("Please at least try to pay attention.. Give it another go champ, you probably understand basic instructions..");
                playerTurn(player, monster);
            }

        }
    }

    private void playerAttack(AHero player, AMonster monster) {
        int damage = player.getDamage();
        player.attack();
        monster.takeDamage(damage);
        System.out.println("A half hearted attack dealt " + damage + " damage to " + monster.getName() + " !");
    }

    private void monsterTurn(AHero player, AMonster monster) {
        int damage = monster.getDamage();
        monster.attack();
        player.takeDamage(damage);
        System.out.println("The " + monster.getName() + " dealt " + damage + " damage to your lethargic bottom");
    }


    private void displayMonsterInfo(AMonster monster) {
        System.out.println("Monster info: ");
        System.out.println(monster.getStatus());
    }
    public void displayCombatStatus(AHero hero, AMonster monster){
        System.out.println("Player info: \n" + hero.getStatus());
        System.out.println("Monster info: \n" + monster.getStatus());
    }

    private void getMonsterLore(AMonster monster) {
        System.out.println("Spectacular lore coming up: \n" + monster.getLore());
    }

    private void displayCombatResult(AHero player, AMonster monster) {
        if (player.isAlive()) {
            System.out.println("Wow, you didn't die. " + monster.getName() + " did though, lmao.");
            player.loot(monster.getExperience(), monster.getGold());
            System.out.println("you got some gold: " + player.getGold() + " and some experience: " + player.getExperience());
        } else {
            System.out.println("You were defeated, how embarrassing, you could try again from the start, but it's okay to admit defeat.");
        }
    }

    public void showAfterFightMenu(AHero hero, AMonster monster) {
        System.out.println("Welcome to the awesome after fight menu, it looks cool but it's the same as the other ones basically.");
        System.out.println("1. - Attack next monster\n 2. - Go to the shop\n 3. - Quit out.");

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> {
                startCombat(hero);
            }

            case 2 -> {
                System.out.println("Going shopping, sure..");
                shop.buyItem(hero, monster);
            }

            case 3 -> { game.exitGame();
            System.out.println("Wouldn't expect you to comeback, but if you're thinking about it, don't. This is not something that gets better the second time you try it.");}
            default -> {
                System.out.println("Hey! Sausage fingers - pay attention and try again.");
                showAfterFightMenu(hero, monster);
            }
        }
    }
        public void tryToFlee(AHero hero, AMonster monster) {
        if (hero.didFlee()) {
            System.out.println("You chickened out. " + monster.getName() + " looks confused..");
        } else {
            System.out.println("Feel like I should give you a penalty for that failed escape, well I'll remove 10 health, seems fair right?");
            hero.takeDamage(10);
            System.out.println("Your new health: " + hero.getHealth());
        }

        }


    public void start() {

    }
}
