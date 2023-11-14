package com.hampusborg.demo.monsters;

public class Denethor extends AMonster {
    public Denethor() {
        super("Denethor", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Running around on fire and tries to catch you";
    }

    @Override
    public String getLore() {
        return "\n" +
                "Denethor, the Steward of Gondor, had a fantastic life hobby: brooding. " +
                "His skills included staring into distant flames, eating tomatoes dramatically, and ignoring the fact that his son Faramir was a decent human being." +
                " Despite having a cool city called Minas Tirith, he preferred a doom-and-gloom approach to leadership. His bright idea was to light himself on fire just to make a point. " +
                "His parenting style wasn't winning any awards, but hey, at least he made an impression.";


    }

}

