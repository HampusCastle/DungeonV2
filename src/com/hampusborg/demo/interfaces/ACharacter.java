package com.hampusborg.demo.interfaces;

public abstract class ACharacter implements ICombat {
    protected String name;
    protected int damage;
    protected int health;
    protected int experience;
    protected int gold;

    public int getHealth() {
        return health;
    }
    public void takeDamage(int damage) {
        health -= damage;
    }
    public boolean isAlive() {
        return health > 0;
    }
    public int getDamage() {
        return damage;
    }
    public int getGold(){
        return this.gold;
    }
    public String getName(){
        return this.name;
    }

    public int getExperience() {
        return experience;
    }

    public String getStatus() {

        return "Name: " + name +
                "\nHealth: " + health +
                "\nDamage: " + getDamage();
    }

}
