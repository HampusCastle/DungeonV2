package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.IColors;

public class Ranger extends AHero implements IColors {

    public Ranger () {
        super("Arrow Fumblefoot the Clumsy Ranger", 10, 10, 10, 1000, 10);
    }

    @Override
    public String attack() {
        return "Uses his slingshot to try and hit the target!";
    }
}
