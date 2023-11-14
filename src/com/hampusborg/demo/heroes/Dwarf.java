package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.ICombat;

public class Dwarf extends AHero implements ICombat {

    public Dwarf() {
        super("Mumble Stone-beard", 10, 10, 10, 1000, 10);
    }

    @Override
    public String attack() {
        return "Swings a mighty axe with decent precision!";
    }
}
