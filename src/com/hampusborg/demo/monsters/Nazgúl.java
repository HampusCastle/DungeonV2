package com.hampusborg.demo.monsters;

public class Nazgúl extends AMonster {
    public Nazgúl() {
        super("Nazgúl", 10, 10, 10, 10);
    }

    @Override
    public String attack() {
        return "Screaming very high and inconvenient";
    }

    @Override
    public String getLore() {
        return "The Nazgûl, or Ringwraiths, were Sauron's fan club – the ultimate groupies in hooded robes." +
                " Sporting snazzy invisible cloaks and screeching like they had laryngitis, they were once kings who got a little too attached to their bling, the Rings of Power. " +
                "Now, they ride around on creepy flying creatures, desperately trying to locate the One Ring, like a bunch of spectral delivery drivers with really bad customer service. " +
                "Their hobbies include sniffing out hobbits, terrorizing the Shire, and asking Frodo if he's seen any shiny jewelry lately. " +
                "Talk about afterlife gone wrong.";
    }
}

