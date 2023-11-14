package com.hampusborg.demo.monsters;

public class Sauron extends AMonster {
    public Sauron() {
        super("Sauron", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Commands a Orc and a Uruk-Hai to throw rocks at you";
    }


    @Override
    public String getLore() {
        return "Sauron, the Dark Lord of Mordor, was the ultimate overachiever in the art of evil." +
                " Forget a snappy wardrobe – he went all out with a giant, fiery eyeball. Practical, really. His idea of world domination involved forging the One Ring, the bling to rule them all. " +
                "Sure, it made him invisible, but it also made him pretty lousy at hide-and-seek. He spent most of his time watching people from his tower, like the original Dark Lord of Netflix and Chill. " +
                "Sauron's management style was all about micromanaging his minions, sending Nazgûl on Ring-fetching missions, and making everyone feel like they were part of a really dysfunctional family. " +
                "In the end, his grand plan unraveled because of a couple of determined hobbits and a jewelry disposal problem. Good effort, Sauron, but maybe next time focus on team-building exercises instead of world domination.";
    }
}
