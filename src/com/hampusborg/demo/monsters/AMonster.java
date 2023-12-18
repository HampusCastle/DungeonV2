package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.ACharacter;
import com.hampusborg.demo.interfaces.ICombat;

import java.util.Random;

public abstract class AMonster extends ACharacter implements ICombat {
    private Long monsterID;
    private int initialHealth;
    private int healthLost;

    public abstract int getHealthLost();

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public int getHealth() {
        return initialHealth;
    }

    public AMonster(String name, int damage, int health, int experience, int gold) {
        super.name = name;
        super.damage = damage;
        super.health = health;
        super.experience = experience;
        super.gold = gold;
        this.initialHealth = health;


    }

    public abstract String getLore();

    public void setMonsterID(Long monsterID) {
        this.monsterID = monsterID;
    }

    public Long getMonsterID() {
        return monsterID;
    }
public void takeDamage(int damage) {
    health -= damage;
    calculateHealthLost();
}
public void setHealth(int health) {
        this.health = health;
}
}
