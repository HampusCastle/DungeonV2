
package com.hampusborg.demo.shop;
import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.database.repository.WeaponDao;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.AMonster;

import java.sql.SQLException;
import java.util.ArrayList;

import static com.hampusborg.demo.database.repository.WeaponDao.INSERT_HERO_WEAPON_SQL;

public class Shop implements IColors {

    DatabaseConnector db;
    private LevelsMenu levelsmenu;
    private ArrayList<Weapon> weapons;
    private WeaponDao weaponDao;

    public Shop () {
         this.weapons = WeaponFactory.generateWeapons();
    }

    public void buyItems(Hero hero, AMonster monster) throws SQLException {
        boolean wrongInput = true;
        this.db = new DatabaseConnector();
        this.levelsmenu = new LevelsMenu(hero);
        this.weaponDao = new WeaponDao();


        do {
            displayShopOptions();
            int choice = Input.getIntInput("Enter what you want to buy: ");
            switch (choice) {
                case 1 -> statsUpgrade(hero, "Health", 10, 120);
                case 2 -> statsUpgrade(hero, "Damage", 10, 150);
                case 3 -> statsUpgrade(hero, "Agility", 10, 120);
                case 4 -> statsUpgrade(hero, "Strength", 10, 200);
                case 5 -> statsUpgrade(hero, "intelligence", 10, 200);
                case 6 -> buyWeapon(hero, monster);
                case 0 -> levelsmenu.showAfterFightMenu(monster);
                default -> {
                    System.out.println("Invalid choice, buy or leave..");
                    wrongInput = true;
                }

            }
        } while (wrongInput);
        levelsmenu.showAfterFightMenu(monster);

    }

    private void buyWeapon(Hero hero, AMonster monster) throws SQLException {

        if (weapons == null) {
            System.out.println("Error, list is empty");
        }
        System.out.println("Look at all these weapons");
        for (Weapon weapon : weapons) {
            System.out.println(weapon.toString());
        }
        System.out.println("0 -> go back");

        int choice = Input.getIntInput("Enter what you want to buy: ");

        if (choice == 0) {
            buyItems(hero, monster);
            return;
        }
        Weapon selectedWeapon = findWeaponById(choice);

        if (selectedWeapon != null && hero.getGold() >= selectedWeapon.getCost()) {
            hero.setGold(hero.getGold() - selectedWeapon.getCost());
            hero.addToInventory(selectedWeapon);
            weaponDao.saveHeroWeapon(hero.getHeroID(), selectedWeapon.getWeaponId(), 1);
            hero.setDamage(hero.getDamage() + selectedWeapon.getDamage());
            System.out.println("You bought: " + selectedWeapon.getName());
            levelsmenu.savePlayer();


        } else if (selectedWeapon == null) {
            System.out.println("Invalid weapon ID. Nothing was bought, obviously??");
        } else {
            System.out.println("Insufficient funds, donut!");
        }
    }

    private Weapon findWeaponById(int weaponId) {
        for (Weapon weapon : weapons) {
           if (weapon.getWeaponId() == weaponId) {
            return weapon;
         }
      }
        return null;
    }

    private void displayShopOptions() {
        System.out.println(BLUE + "1. - Health pot: 120g || Gives Health +10 " + RESET);
        System.out.println(RED + "2. - Extra spikes for a club : 150g || Gives: Damage +10" + RESET);
        System.out.println(GREEN + "3. - Judo lesson: 130g || Gives: Agility +10 " + RESET);
        System.out.println(YELLOW + "4. - A day with a personal trainer: 200g || Gives: Strength +10" + RESET);
        System.out.println(BLUE + "5. - A book: 200g || Gives: Intelligence +10" + RESET);
        System.out.println(GREEN + "6. - A solid choice of weapons" + RESET);
        System.out.println(RED + "0. - Exit" + RESET);
    }

    private void statsUpgrade(Hero hero, String statName, int statIncrease, int cost) {
        if (hero.getGold() >= cost) {
            statIncrease(hero, statName, statIncrease);
            hero.setGold(hero.getGold() - cost);
            System.out.println(GREEN + "New stats: " + hero.getStatus() + RESET);
        } else {
            System.out.println(YELLOW + "Insufficient funds. Get Lost!" + RESET);
        }
    }

    private void statIncrease(Hero hero, String statName, int statIncrease) {
        switch (statName) {
            case "Health" -> hero.setHealth(hero.getHealth() + statIncrease);
            case "Damage" -> hero.setDamage(hero.getDamage() + statIncrease);
            case "Agility" -> hero.setAgility(hero.getAgility() + statIncrease);
            case "Strength" -> hero.setStrength(hero.getStrength() + statIncrease);
            case "Intelligence" -> hero.setIntelligence(hero.getIntelligence() + statIncrease);
        }
    }


}
