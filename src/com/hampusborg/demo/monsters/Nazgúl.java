package com.hampusborg.demo.monsters;

import com.hampusborg.demo.interfaces.IColors;

public class Nazgúl extends AMonster implements IColors {
    public Nazgúl() {
        super("Nazgúl", 25, 55, 17, 13);
    }

    @Override
    public String attack() {
        return YELLOW + "Screaming very high and inconvenient" + RESET;
    }

    @Override
    public String getLore() {
        return YELLOW + "The Nazgûl, or Ringwraiths, were Sauron's fan club – the ultimate groupies in hooded robes.\n" +
                " Sporting snazzy invisible cloaks and screeching like they had laryngitis, they were once kings who got a little too attached to their bling, the Rings of Power.\n " +
                "Now, they ride around on creepy flying creatures, desperately trying to locate the One Ring, like a bunch of spectral delivery drivers with really bad customer service.\n " +
                "Their hobbies include sniffing out hobbits, terrorizing the Shire, and asking Frodo if he's seen any shiny jewelry lately.\n " +
                "Talk about afterlife gone wrong." + RESET;
    }
}

