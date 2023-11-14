package com.hampusborg.demo.monsters;

public class SarumanTheWhite extends AMonster {
    public SarumanTheWhite() {
        super("Saruman", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Making something.. SUDDENLY throws it at your face!";
    }

    @Override
    public String getLore() {
        return "\n" +
                "Saruman the White, or as he liked to call himself, \"Saruman the Fashionably Pale,\" was the head of the Istari with a serious case of wizard envy." +
                " He had a pretty sweet gig as the head of the White Council, but then he decided that a change in color scheme was in order. " +
                "Turning to the dark side, he traded his staff for a dodgy alliance with Sauron, thinking Mordor real estate was about to skyrocket. " +
                "He also developed a bizarre fascination with industrializing Isengard, turning it into Middle-earth's first heavily polluted factory." +
                " His downfall involved a serious lack of job satisfaction, a shaky alliance with orcs, and a wizard battle that probably would've been more entertaining with a popcorn stand." +
                " All in all, Saruman's career transition from \"Wise\" to \"Worst Decision Maker\" was a shining example of how not to wizard.";
    }
}

