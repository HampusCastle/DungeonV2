package com.hampusborg.demo.monsters;

public class Gothmog extends AMonster {
    public Gothmog() {
        super("Gothmog", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Bad breath, that spurts fire!";
    }


    @Override
    public String getLore() {
        return "Gothmog, the Lieutenant of Morgul, was the guy Sauron called when he needed someone to lead the evil army." +
                " With a name that sounds like it belongs to a death metal band's lead singer, Gothmog had a knack for orchestrating absolute fucking chaos during the Battle of the Pelennor Fields." +
                " He probably got the job by convincing Sauron that spikes on helmets were still in fashion." +
                " While not the most memorable character, he did his villainous duty, shouting orders and causing general mayhem until meeting his untimely end. Cheers to you, Gothmog, the dark lord of battle coordination.";
    }
}

