package com.hampusborg.demo.menus;

import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.monsters.AMonster;
import com.hampusborg.demo.monsters.MonsterFactory;
import com.hampusborg.demo.shop.Shop;

import static java.lang.System.exit;

public class LevelsMenu implements IColors {
    Game game = new Game();
    Shop shop = new Shop();
    private MonsterFactory monsterFactory;

    public LevelsMenu() {
        this.monsterFactory = new MonsterFactory();
    }

    public void startCombat(AHero hero) {
        AMonster monster = monsterFactory.spawnMonster();
        System.out.println(YELLOW + "A odd looking creature " + monster.getName() + " seems to be walking towards you.." + RESET);
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
        System.out.println(RED + "1. - Attack\n 2. - Super awsome and cool monsterinfo \n 3. - Show abilities(and of course sneak peak on your opponent).\n 4. - Try to escape.." + RESET);

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> playerAttack(player, monster);
            case 2 -> {
                getMonsterLore(monster);
                playerTurn(player, monster);
            }
            case 3 -> {
                displayCombatStatus(player, monster);
                playerTurn(player, monster);
                ;
            }
            case 4 -> {
                System.out.println(CYAN + "Now he's trying to leave, oh my god." + RESET);
                tryToFlee(player, monster);
                startCombat(player);
            }
            default -> {
                System.out.println(PURPLE + "Please at least try to pay attention.. Give it another go champ, you probably understand basic instructions.." + RESET);
                playerTurn(player, monster);
            }

        }
    }

    private void playerAttack(AHero player, AMonster monster) {
        int damage = player.getDamage();
        player.attack();
        monster.takeDamage(damage);
        System.out.println(BLUE + "A half hearted attack dealt " + damage + " damage to " + monster.getName() + " !" + RESET);
    }

    private void monsterTurn(AHero player, AMonster monster) {
        int damage = monster.getDamage();
        monster.attack();
        player.takeDamage(damage);
        System.out.println(YELLOW + "The " + monster.getName() + " dealt " + damage + " damage to your lethargic bottom" + RESET);
    }


    private void displayMonsterInfo(AMonster monster) {
        System.out.println(CYAN + "Monster info: " + RESET);
        System.out.println(monster.getStatus());
    }

    public void displayCombatStatus(AHero hero, AMonster monster) {
        System.out.println(RED + "Player info: \n" + hero.getStatus() + RESET);
        System.out.println(BLUE + "Monster info: \n" + monster.getStatus() + RESET);
    }

    private void getMonsterLore(AMonster monster) {
        System.out.println(RED + "Spectacular lore coming up: \n" + monster.getLore() + RESET);
    }

    private void displayCombatResult(AHero player, AMonster monster) {
        if (player.isAlive()) {
            System.out.println(CYAN + "Wow, you didn't die. " + monster.getName() + " did though, lmao." + RESET);
            player.loot(monster.getExperience(), monster.getGold());
            System.out.println(YELLOW + "You now have: " + player.getGold() + " gold, as well as: " + player.getExperience() + " experience!" + RESET);
        } else {
            System.out.println(GREEN + "You were defeated, how embarrassing, you could try again from the start, but it's okay to admit defeat." + RESET);
            exit(0);
        }
    }

    public void showAfterFightMenu(AHero hero, AMonster monster) {
        System.out.println(RED + "Welcome to the awesome after fight menu, it looks cool but it's the same as the other ones basically." + RESET);
        System.out.println(GREEN + "1. - Attack next monster\n 2. - Go to the shop\n 3. - Quit out." + RESET);

        int choice = Input.getIntInput("");

        switch (choice) {
            case 1 -> {
                startCombat(hero);
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
                showAfterFightMenu(hero, monster);
            }
        }
    }

    public void tryToFlee(AHero hero, AMonster monster) {
        if (hero.didFlee()) {
            System.out.println(YELLOW + "You chickened out. " + monster.getName() + " looks confused.." + RESET);
        } else {
            System.out.println(RED + "Feel like I should give you a penalty for that failed escape, well I'll remove 10 health, seems fair right?" + RESET);
            hero.takeDamage(10);
            System.out.println(GREEN + "Your new health: " + hero.getHealth() + RESET);
        }

    }


}
