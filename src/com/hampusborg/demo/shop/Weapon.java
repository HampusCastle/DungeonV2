package com.hampusborg.demo.shop;
public class Weapon {
    private int weaponId;
    private String name;
    private int damage;
    private int cost;



    public int getCost() {
        return cost;
    }
    public int setCost(int cost) {
        return cost;
    }


public Weapon() {}
public Weapon (int weaponId, String name, int damage, int cost) {
    this.weaponId = weaponId;
    this.name = name;
    this.damage = damage;
    this.cost = cost;
}
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
    public String setName(String name) {
        return this.name;
    }

}


