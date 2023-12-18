package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class Gothmog extends AMonster implements IColors {
    public Gothmog() {
        super("Gothmog", 30, 50, 45, 30);
    }

    private int healthLost;
    @Override
    public int getLostHealth() {
        return healthLost;
    }

    @Override
    public String attack() {
        return BLUE + "Bad breath, that spurts fire!" + RESET;
    }


    @Override
    public int getHealthLost() {
        return 0;
    }

    @Override
    public String getLore() {
        return "Gothmog, the Lieutenant of Morgul, was the guy Sauron called when he needed someone to lead the evil army.\n" +
                " With a name that sounds like it belongs to a death metal band's lead singer, Gothmog had a knack for orchestrating absolute fucking chaos during the Battle of the Pelennor Fields.\n" +
                " He probably got the job by convincing Sauron that spikes on helmets were still in fashion." +
                " While not the most memorable character, he did his villainous duty, shouting orders and causing general mayhem until meeting his untimely end.\n Cheers to you, Gothmog, the dark lord of battle coordination.";
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        healthLost += damage;
        calculateHealthLost();
    }
}

