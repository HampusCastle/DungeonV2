/*
package com.hampusborg.demo.heroes;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAHero extends AHero {


    public TestAHero(String name, int strength, int agility, int damage, int health, int intelligence) {
        super(name, strength, agility, damage, health, intelligence);
    }


    @Test
    public void testIsAliveIfPlayerDies() {
        AHero hero = new TestAHero("Test Hero", 1, 1, 1, 1, 1);
        hero.setHealth(0);
        assertFalse(hero.isAlive());
    }

    @Test
    public void testIsAliveIfPlayerLives() {
        AHero hero = new TestAHero("Test Hero", 1, 1, 1, 1, 1);
        hero.setHealth(10);
        assertTrue(hero.isAlive());
    }

    public void testExperienceLevelUp() {
        AHero hero = new TestAHero("Test Hero", 1, 1, 1, 1, 1);
        hero.setLevel(1);
        hero.setExperience(95);
        hero.setExperience(10);

        assertEquals(2, hero.getLevel());
        assertEquals(3, hero.getExperience());
        assertEquals(3, hero.getAgility());
        assertEquals(3, hero.getStrength());
        assertEquals(3, hero.getIntelligence());

        System.out.println();

    }


    @Override
    public String attack() {
        return null;
    }
}
*/
