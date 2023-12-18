package com.hampusborg.demo.heroes;
import com.hampusborg.demo.interfaces.ACharacter;
import com.hampusborg.demo.shop.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends ACharacter {
    Random r = new Random();
    private int strength;
    private int agility;
    private int intelligence;
    protected int level;
    protected int monstersKilled;
    protected Long heroID;
    private String heroClass;
    private String name;
    protected List<Weapon> weapons;
    protected Weapon weapon;
    private Hero hero;
    private int healthLost;

    public int getLostHealth() {
        return healthLost;
    }

    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setMonstersKilled(int monstersKilled) {
        this.monstersKilled = monstersKilled;
    }

    public  void setHeroID(Long heroID) {
        this.heroID = heroID;
    }

    public Weapon getWeapon() {
        return weapon;
    }


    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }



    public Long getHeroID() {
        return heroID;
    }
    public int getMonstersKilled() {
        return monstersKilled;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

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

    public int setAgility(int agility) {
        return this.agility = agility;
    }

    public int setDamage(int damage) {
        return this.damage = damage;
    }

    public int getGold() {
        return this.gold;
    }

    public int setGold(int gold) {
        return this.gold = gold;
    }


    public int getHealth() {
        return health;
    }

    public int setHealth(int health) {
        return this.health = health;
    }

    public Hero(String playerName, String heroClass, int strength, int agility, int damage, int health, int intelligence) {
        this.name = playerName;
        this.heroClass = heroClass;
        this.strength = strength;
        this.agility = agility;
        this.damage = damage;
        this.health = health;
        this.intelligence = intelligence;
        this.weapons = new ArrayList<>();
        this.experience = 0;
        this.gold = 0;
        this.level = 1;
        this.initialHealth = health;

    }
    public Hero () {}

    public void levelUp() {

        level += 1;
        agility += 2;
        strength += 2;
        intelligence += 2;
        damage += 2;
        health = 100;
        System.out.println("You have reached a new level, DING!\n" +
                level + " is your new level!\n" +
                agility + " is you new agility level!\n" +
                strength + " is your new strength level!\n" +
                damage + " is now your new damage level!\n" +
                intelligence + " is you new intelligence level!\n" +
                "Your health has also been reset and is now: " + health);

    }


    public boolean didFlee() {
        return r.nextInt(100) < 60 + agility;
    }

    private boolean isCriticalHit(boolean isRegularAttack) {
        if (isRegularAttack &&  r.nextInt(100) < intelligence) {
            return true;
        }
        return false;
    }


    public int getDamage(boolean isRegularAttack) {
        return calculateDamage(isRegularAttack);
    }

    private int calculateDamage(boolean isRegularAttack) {
        int tempDamage = damage + (strength * 2 / 4 + 1);
        if (isCriticalHit(isRegularAttack)) {
            System.out.println("Cracking hit son! Critical strike dealt: " + tempDamage * 2);
            return tempDamage * 2;
        }
        System.out.println("Week hit, only: " + tempDamage);

        return tempDamage;
    }

    public void loot(int gold, int experience) {
        monstersKilled++;
        this.gold += gold;
        setExperience(experience);
    }

    public void setExperience(int experience) {
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
                "\nDamage: " + damage +
                "\nStrength: " + strength +
                "\nAgility: " + agility +
                "\n Experience: " + experience +
                "\nGold: " + gold +
                "\nLevel: " + level;

    }


    public void addToInventory(Weapon selectedWeapon) {
            if (this.weapons == null) {
                this.weapons = new ArrayList<>();
            }
            this.weapons.add(selectedWeapon);
    }

    public String toSaveFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hero:").append(name).append(" level ").append(level).append("\nWeapons:");
        for (Weapon weapon : weapons
        ) {
            sb.append(weapon.getName()).append("\n");
        }
        sb.append("Monsters killed: ").append(monstersKilled);
        return sb.toString();
    }

    @Override
    public String attack() {
        int damage = calculateDamage(true);
        return "Player hits for " + damage + " damage!";
    }
}

