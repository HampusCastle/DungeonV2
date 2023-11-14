package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.ICombat;

public class Wizard extends AHero implements ICombat {


    public Wizard() {
        super("Fizzlenoodle the Enchantingly Clumsy", 10, 10, 10, 1000, 10);
    }

    @Override
    public String attack() {
        return "Does some wizardly shit, zap zap ";
    }
}
