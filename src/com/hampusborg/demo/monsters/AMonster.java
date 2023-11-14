package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.ACharacter;
import com.hampusborg.demo.interfaces.ICombat;

import java.util.Random;

public abstract class AMonster extends ACharacter implements ICombat {

    public AMonster(String name, int damage, int health, int experience, int gold) {
        super.name = name;
        super.damage = damage;
        super.health = health;
        super.experience = experience;
        super.gold = gold;
    }
    public abstract String getLore();


  }
