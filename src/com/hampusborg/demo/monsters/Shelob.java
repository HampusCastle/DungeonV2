package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.interfaces.ICombat;

public class Shelob extends AMonster implements ICombat, IColors {

    public Shelob() {
        super("Shelob", 25, 65, 55, 35);
    }

    private int healthLost;
    @Override
    public int getLostHealth() {
        return healthLost;
    }

    @Override
    public String attack() {
        return "Hides in a dark place... shoots string everywhere and making a mess..";
    }

    @Override
    public int getHealthLost() {
        return 0;
    }

    @Override
    public String getLore() {
        return YELLOW + "Meet Shelob, the overgrown arachnid influencer of Mordor. Living in her chic lair in Cirith Ungol, she thought ordinary spider activities like catching flies were too mainstream.\n" +
                " So, she upsized, grew to the size of a small building, and became the Elle Woods of the spider world \n. Shelob's hobbies included scaring the living daylights out of travelers, making the pass of Cirith Ungol the go-to destination for thrill-seekers." +
                " Her interior decorating skills were on point, with webs strategically placed to give that spooky, abandoned vibe.\n " +
                "But what really set her apart was her affinity for bling \n – she had a thing for the One Ring, using it as her very own oversized, all-you-can-eat buffet bait.\n " +
                "Who needs flies when you can have a hobbit with a side of precious jewelry, right? Talk about a spider with expensive taste." + RESET;
    }
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        healthLost += damage;
        calculateHealthLost();
    }
}
