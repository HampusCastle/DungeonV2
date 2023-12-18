package com.hampusborg.demo.menus;
import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.database.repository.HeroDao;
import com.hampusborg.demo.heroes.*;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;

import java.util.Scanner;

public class HeroMenu implements IColors {

    Scanner scanner = new Scanner(System.in);
    HeroDao heroDao;
    Hero hero;
    DatabaseConnector db;
    public Hero choosePlayer() {

        this.db = new DatabaseConnector();
        this.db.openConnection();
        this.heroDao = new HeroDao();
        String playerName = getPlayerName();


        System.out.println(" Choose class: \n 1. - Ranger \n 2. - Warrior \n 3. - Wizard \n 4. - Dwarf");

        switch (Input.getIntInput("")) {
            case 1 -> {
                System.out.println(RED + "The ranger, well shooting people from a distance seems like something you would choose. You don't really look like a physical combat type for person.." + RESET);
                this.hero = new Ranger(playerName);
                heroDao.saveHeroToDatabase(hero, this.db);
                return hero;
            }
            case 2 -> {
                System.out.println(YELLOW + "The warrior, I have to give it to you, if I had to guess I wouldn't say you look like a brute type of person." + RESET);
                this.hero = new Warrior(playerName);
                heroDao.saveHeroToDatabase(hero, this.db);
                return hero;
            }
            case 3 -> {
                System.out.println(BLUE + "The Wizard, everyone wants to be like Gandalf but end up looking like Harry Potter if he didn't have no magical powers. But hey, you might be different." + RESET);
                this.hero = new Wizard(playerName);
                heroDao.saveHeroToDatabase(hero, this.db);
                return hero;
            }
            case 4 -> {
                System.out.println(PURPLE + "Swinging axes and emptying kegs. Well, to be fair, if I was a dwarf living in Moria. Look it up." + RESET);
                this.hero = new Dwarf(playerName);
                heroDao.saveHeroToDatabase(hero, this.db);
                return hero;

            }

            default -> {
                System.out.println(CYAN + "Faulty input, give it another try, and put some effort in for the love of god.." + RESET);
                return choosePlayer();
            }
        }
    }
    public String getPlayerName() {
        System.out.println("Enter your hero's name:");
        String playerName = scanner.nextLine();

        if (!isValidName(playerName)) {
            System.out.println("Invalid name! Please use only letters (A-Z, a-z).");
            return getPlayerName();
        }

        return playerName;
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}

