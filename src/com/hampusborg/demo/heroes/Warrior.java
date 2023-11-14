package com.hampusborg.demo.heroes;

public class Warrior extends AHero {
    public Warrior() {
        super("Sir Blunderblade the Bumbling", 10, 10, 10, 1000, 10);
    }

    @Override
    public String attack() {
        return "Wields a bad ass sword which is way to heavy, but here it goes";
    }
}
