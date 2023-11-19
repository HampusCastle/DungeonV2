

package com.hampusborg.demo.shop;

import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.AMonster;


import java.util.ArrayList;


public class Shop implements IColors {

    private LevelsMenu levelsmenu;
    private ArrayList<Weapon> weapons;

    public Shop () {
         this.weapons = WeaponFactory.generateWeapons();
    }

    public void buyItems(AHero hero, AMonster monster) {
        boolean wrongInput = true;
        this.levelsmenu = new LevelsMenu();


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
                case 0 -> levelsmenu.showAfterFightMenu(hero, monster);
                default -> {
                    System.out.println("Invalid choice, buy or leave..");
                    wrongInput = true;
                }

            }
        } while (wrongInput);
        levelsmenu.showAfterFightMenu(hero, monster);

    }

    private void buyWeapon(AHero hero, AMonster monster) {

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
            hero.setDamage(hero.getDamage() + selectedWeapon.getDamage());
            System.out.println("You bought: " + selectedWeapon.getName());
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

    private void statsUpgrade(AHero hero, String statName, int statIncrease, int cost) {
        if (hero.getGold() >= cost) {
            statIncrease(hero, statName, statIncrease);
            hero.setGold(hero.getGold() - cost);
            System.out.println(GREEN + "New stats: " + hero.getStatus() + RESET);
        } else {
            System.out.println(YELLOW + "Insufficient funds. Get Lost!" + RESET);
        }
    }

    private void statIncrease(AHero hero, String statName, int statIncrease) {
        switch (statName) {
            case "Health" -> hero.setHealth(hero.getHealth() + statIncrease);
            case "Damage" -> hero.setDamage(hero.getDamage() + statIncrease);
            case "Agility" -> hero.setAgility(hero.getAgility() + statIncrease);
            case "Strength" -> hero.setStrength(hero.getStrength() + statIncrease);
            case "Intelligence" -> hero.setIntelligence(hero.getIntelligence() + statIncrease);
        }
    }


}
