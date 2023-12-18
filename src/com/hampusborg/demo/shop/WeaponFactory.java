package com.hampusborg.demo.shop;
import java.util.ArrayList;

public class WeaponFactory {
    public static ArrayList<Weapon> generateWeapons() {
        Weapon w1 = new Weapon(1,"Cool bow", 4, 100);
        Weapon w2 = new Weapon(2,"Butter knife", 1, 50);
        Weapon w3 = new Weapon(3,"Sugar cane", 100, 1000);
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(w1);
        weapons.add(w2);
        weapons.add(w3);
        return weapons;
    }

    public static Weapon getWeaponById(int weaponID) {
        for (Weapon weapon : generateWeapons()) {
            if (weapon.getWeaponId() == weaponID) {
                return weapon;
            }
        }
        return null;
    }
}
