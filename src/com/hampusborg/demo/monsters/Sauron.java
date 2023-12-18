package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class Sauron extends AMonster implements IColors {
    public Sauron() {
        super("Sauron", 40, 75, 75, 55);
    }

    private int healthLost;
    @Override
    public int getLostHealth() {
        return healthLost;
    }

    @Override
    public String attack() {
        return "Commands a Orc and a Uruk-Hai to throw rocks at you";
    }


    @Override
    public int getHealthLost() {
        return 0;
    }

    @Override
    public String getLore() {
        return BLUE + "Sauron, the Dark Lord of Mordor, was the ultimate overachiever in the art of evil.\n" +
                " Forget a snappy wardrobe – he went all out with a giant, fiery eyeball.\n Practical, really. His idea of world domination involved forging the One Ring, the bling to rule them all.\n " +
                "Sure, it made him invisible, but it also made him pretty lousy at hide-and-seek. \n He spent most of his time watching people from his tower, like the original Dark Lord of Netflix and Chill.\n " +
                "Sauron's management style was all about micromanaging his minions, sending Nazgûl on Ring-fetching missions, and making everyone feel like they were part of a really dysfunctional family.\n " +
                "In the end, his grand plan unraveled because of a couple of determined hobbits and a jewelry disposal problem.\n Good effort, Sauron, but maybe next time focus on team-building exercises instead of world domination." + RESET;
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        healthLost += damage;
        calculateHealthLost();
    }
}
