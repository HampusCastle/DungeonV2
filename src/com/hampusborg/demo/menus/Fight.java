package com.hampusborg.demo.menus;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.monsters.AMonster;

import java.util.Date;

public class Fight {
    private Long fightID;
    private Long heroID;
    private Long monsterID;
    private Date timestamp;
    private String winner;
    private int heroInitialHealth;
    private int monsterInitialHealth;
    private int heroHealthLost;
    private int monsterHealthLost;


    public void setDamageDealt(int damageDealt) {
        this.damageDealt = damageDealt;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    private int damageDealt;

    public void setHeroInitialHealth(int heroInitialHealth) {
        this.heroInitialHealth = heroInitialHealth;
    }

    public void setMonsterInitialHealth(int monsterInitialHealth) {
        this.monsterInitialHealth = monsterInitialHealth;
    }

    public int getHeroInitialHealth() {
        return heroInitialHealth;
    }

    public int getMonsterInitialHealth() {
        return monsterInitialHealth;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }


    public Fight(Hero hero, AMonster monster) {
        this.monsterInitialHealth = monster.getHealth();
        this.heroInitialHealth = hero.getHealth();
        this.heroHealthLost = 0;
        this.monsterHealthLost = 0;

    }


    public void setFightID(Long fightID) {
        this.fightID = fightID;
    }

    public void setHeroID(Long heroID) {
        this.heroID = heroID;
    }

    public void setMonsterID(Long monsterID) {
        this.monsterID = monsterID;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setHeroHealthLost(int heroHealthLost) {
        this.heroHealthLost = heroHealthLost;
    }

    public void setMonsterHealthLost(int monsterHealthLost) {
        this.monsterHealthLost = monsterHealthLost;
    }

    public Long getFightID() {
        return fightID;
    }

    public Long getHeroID() {
        return heroID;
    }

    public Long getMonsterID() {
        return monsterID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getHeroHealthLost() {
        return heroHealthLost;
    }

    public int getMonsterHealthLost() {
        System.out.println("Monster Health Lost (getMonsterHealthLost): " + monsterHealthLost);
        return monsterHealthLost;
    }

}