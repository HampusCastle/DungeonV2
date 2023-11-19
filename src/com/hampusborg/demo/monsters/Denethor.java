package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class Denethor extends AMonster implements IColors {
    public Denethor() {
        super("Denethor", 20, 40, 35, 15);
    }

    @Override
    public String attack() {
        return RED + "Running around on fire and tries to catch you" + RESET;
    }

    @Override
    public String getLore() {
        return "\n" + RED +
                "Denethor, the Steward of Gondor, had a fantastic life hobby: brooding. \n" +
                "His skills included staring into distant flames, eating tomatoes dramatically, and ignoring the fact that his son Faramir was a decent human being.\n" +
                " Despite having a cool city called Minas Tirith, he preferred a doom-and-gloom approach to leadership. His bright idea was to light himself on fire just to make a point.\n " +
                "His parenting style wasn't winning any awards, but hey, at least he made an impression." + RESET;


    }

}

