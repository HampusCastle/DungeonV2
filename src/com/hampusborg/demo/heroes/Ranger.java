package com.hampusborg.demo.heroes;

import com.hampusborg.demo.interfaces.IColors;

public class Ranger extends Hero implements IColors {

    public Ranger () {
        super("Arrow Fumblefoot the Clumsy Ranger", "Ranger", 20, 35, 40, 100, 25);
    }

    @Override
    public String attack() {
        return "Uses his slingshot to try and hit the target!";
    }
}
