package com.hampusborg.demo.shop;
public class Weapon {
    public int getCost() {
        return cost;
    }

    public Weapon(int weaponId, String name, int damage, int cost) {

        this.weaponId = weaponId;
        this.name = name;
        this.damage = damage;
        this.cost = cost;

    }

    private final int weaponId;
    private final String name;
    private final int damage;
    private final int cost;

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return weaponId + " Name: " + name + "\nDamage: " + damage + "\nCost: " + cost;
    }


    public int getWeaponId() {
        return weaponId;
    }
}


