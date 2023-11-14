package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.ACharacter;

import java.util.Random;

public abstract class AHero extends ACharacter {
    Random r;
    private int strength;
    private int agility;
    private int intelligence;
    private int level;


    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getAgility() {
        return agility;
    }
    public int setAgility (int agility) {
        return this.agility = agility;
    }
    public int setDamage(int damage) {
        return this.damage = damage;
    }
    public int getGold() {
        return this.gold;
    }
    public int setGold(int gold) {
        return gold;
    }


    public int getHealth() {
        return health;
    }

    public int setHealth(int i) {
        return this.health = health;
    }

    public AHero(String name, int strength, int agility, int damage, int health, int intelligence) {
        super.name = name;
        this.strength = strength;
        this.agility = agility;
        this.damage = damage;
        this.health = health;
        this.intelligence = intelligence;
        this.r = new Random();
        this.experience = 0;
        this.gold = 0;
        this.level = 1;

    }

    public void levelUp() {

        level += 1;
        agility += 2;
        strength += 2;
        intelligence += 2;
        health = 100;
        System.out.println("You have reached a new level, DING!\n" +
                level + " is your new level!\n" +
                agility + "is you new agility level!\n" +
                strength + "is your new strength level!" +
                intelligence + "is you new intelligence level!\n Your Health have also been reset to: " + health);

    }


    public boolean didFlee() {
        return r.nextInt(100) < 60 + agility;
    }

    private boolean didDodge() {
        return r.nextInt(100) < agility;
    }

    private boolean isCriticalHit() {
        return r.nextInt(100) < intelligence;
    }

    @Override
    public int getDamage() {
        return calculateDamage();
    }

    private int calculateDamage() {
        int tempDamage = damage + (strength * 2 / 4 + 1);
        if (isCriticalHit()) {
            return tempDamage * 2;
        }
        return tempDamage;
    }

    public void loot(int gold, int experience) {
        this.gold += gold;
        setExperience(experience);
    }

    private void setExperience(int experience) {
        if (this.experience + experience >= 100) {
            levelUp();
            this.experience = (this.experience + experience) - 100;
        } else {
            this.experience += experience;
        }
    }



    public String getStatus() {

        return "Name: " + name +
                "\nHealth: " + health +
                "\nDamage: " + getDamage() +
                "\nStrength: " + strength +
                "\nAgility: " + agility +
                "\nGold: " + gold +
                "\nLevel: " + level;

    }

    public void flee() {
        System.out.println(name + " tries to flee");
        if (didFlee()) {
            System.out.println("He managed to get away, almost weasel like...");
        } else {
            System.out.println("HA! failed escape, now fight!");
        }

    }

    public String dodge() {
        return "The attack was dodged and no hp was lost!";
    }
}

