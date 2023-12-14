package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.ICombat;

public class Wizard extends Hero implements ICombat {


    public Wizard() {
        super("Fizzlenoodle the Enchantingly Clumsy", "Wizard", 15, 20, 37, 100, 45);
    }

    @Override
    public String attack() {
        return "Does some wizardly shit, zap zap ";
    }
}
