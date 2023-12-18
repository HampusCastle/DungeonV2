package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class SarumanTheWhite extends AMonster implements IColors {
    public SarumanTheWhite() {
        super("Saruman", 47, 60, 40, 47);
    }
private int healthLost;
    @Override
    public int getLostHealth() {
        return healthLost;
    }

    @Override
    public String attack() {
        return "Making something.. SUDDENLY throws it at your face!";
    }

    @Override
    public int getHealthLost() {
        return 0;
    }

    @Override
    public String getLore() {
        return "\n" + RED +
                "Saruman the White, or as he liked to call himself, \"Saruman the Fashionably Pale,\" was the head of the Istari with a serious case of wizard envy.\n" +
                " He had a pretty sweet gig as the head of the White Council, but then he decided that a change in color scheme was in order.\n " +
                "Turning to the dark side, he traded his staff for a dodgy alliance with Sauron, thinking Mordor real estate was about to skyrocket. \n" +
                "He also developed a bizarre fascination with industrializing Isengard, turning it into Middle-earth's first heavily polluted factory.\n" +
                " His downfall involved a serious lack of job satisfaction\n, a shaky alliance with orcs, and a wizard battle that probably would've been more entertaining with a popcorn stand.\n" +
                " All in all, Saruman's career transition from \"Wise\" to \"Worst Decision Maker\" was a shining example of how not to wizard." + RESET;
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        healthLost += damage;
        calculateHealthLost();
    }
}

