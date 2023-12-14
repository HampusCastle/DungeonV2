package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.ICombat;

public class Dwarf extends Hero implements ICombat {

    Hero hero;
    public Dwarf() {
        super("Mumble Stone-beard", "Dwarf",35, 20, 40, 100, 25);
    }

    @Override
    public String attack() {
        return "Swings a mighty axe with decent precision!";
    }
}
