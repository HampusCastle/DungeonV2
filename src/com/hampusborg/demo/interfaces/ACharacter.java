package com.hampusborg.demo.interfaces;

public abstract class ACharacter implements ICombat {
    protected String name;
    protected int damage;
    protected int health;
    protected int experience;
    protected int gold;
    protected int initialHealth;

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    int healthLost;

    public ACharacter() {
        this.initialHealth = health;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getHealthLost() {
        return healthLost;
    }


    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        System.out.println("Before character damage: " + health);
        health -= damage;
        System.out.println("after character damage: " + health);

    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getDamage() {
        return damage;
    }

    public int getGold() {
        return this.gold;
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return experience;
    }

    public String getStatus() {

        return "Name: " + name +
                "\nHealth: " + health +
                "\nDamage: " + damage;
    }

    public abstract int getLostHealth();

    public void calculateHealthLost() {
        this.healthLost = this.initialHealth - health;
    }

    }


