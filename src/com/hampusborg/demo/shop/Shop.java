

package com.hampusborg.demo.shop;

import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.AMonster;


import java.util.Scanner;


public class Shop {
    private LevelsMenu levelsMenu;

        public void buyItem(AHero hero, AMonster monster) {
            boolean wrongInput = true;
            this.levelsMenu = new LevelsMenu();

            do {
                System.out.println("1. - Health pot: 120g || Gives Health +20 ");
                System.out.println("2. - Extra spikes for a club : 150g || Gives: Damage +10");
                System.out.println("3. - Judo lesson: 130g || Gives: Agility +20");
                System.out.println("4. - A day with a personal trainer: 200g || Gives: Strength +10");
                System.out.println("5. - A book: 250g || Gives: Intelligence +10");
                System.out.println("0. - Exit");

                int choice = Input.getIntInput("Enter what you want to buy: ");

                switch (choice) {
                    case 1 -> {
                        if (hero.getGold() >= 120) {
                            hero.setHealth(hero.getHealth() + 20);
                            hero.setGold(hero.getGold() - 120);
                            System.out.println("New health: " + hero.getHealth());
                        } else {
                            System.out.println("You don't have enough money");
                        }
                    }
                    case 2 -> {
                        if (hero.getGold() >= 150) {
                            hero.setDamage(hero.getDamage() + 10);
                            hero.setGold(hero.getGold() - 150);
                            System.out.println("New damage: " + hero.getDamage());
                        } else {
                            System.out.println("Insufficient funding, my friend.");
                        }
                    }
                    case 3 -> {
                        if (hero.getGold() >= 130) {
                            hero.setAgility(hero.getAgility() + 20);
                            hero.setGold(hero.getGold() - 130);
                            System.out.println("New agility: " + hero.getAgility());
                        } else {
                            System.out.println("Not enough money.");
                        }
                    }
                    case 4 -> {
                        if (hero.getGold() >= 200) {
                            hero.setStrength(hero.getStrength() + 10);
                            hero.setGold(hero.getGold() - 200);
                            System.out.println("New strength: " + hero.getStrength());
                        } else {
                            System.out.println("Insufficient funds. Get lost!");
                        }
                    }
                    case 5 -> {
                        if (hero.getGold() >= 250) {
                            hero.setIntelligence(hero.getIntelligence() + 10);
                            hero.setGold(hero.getGold() - 250);
                            System.out.println("New intelligence: " + hero.getIntelligence());
                        } else {
                            System.out.println("Stop wasting time. Insufficient funds.");
                        }
                    }
                    case 0 -> wrongInput = false;
                    default -> System.out.println("Invalid choice. Buy something or leave.");
                }

            } while (wrongInput);
            levelsMenu.showAfterFightMenu(hero, monster);
        }
    }
