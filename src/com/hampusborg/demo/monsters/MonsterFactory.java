package com.hampusborg.demo.monsters;

import java.util.Random;

public class MonsterFactory {

    public static AMonster spawnMonster() {
        Random random = new Random();
        int randomIndex = random.nextInt(5);

        switch (randomIndex) {
            case 0:
                return new Denethor();
            case 1:
                return new Gothmog();
            case 2:
                return new Nazgúl();
            case 3:
                return new SarumanTheWhite();
            case 4:
                return new Sauron();
            default:
                throw new IllegalStateException("Unexpected value: " + randomIndex);
        }
    }
}
