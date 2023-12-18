package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.shop.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WeaponDao {
    private Connection db;
    private static final String SELECT_WEAPON_BY_ID_SQL = "SELECT * FROM Weapon WHERE weaponID = ?";
    public static String INSERT_HERO_WEAPON_SQL = "INSERT INTO HeroWeapon (heroID, weaponID, quantity, cost) VALUES (?,?,?, ?)";

    public WeaponDao() {
        this.db = new DatabaseConnector().getConnection();
    }

    public Weapon getWeaponById(int weaponID) {
        try (PreparedStatement statement = db.prepareStatement(SELECT_WEAPON_BY_ID_SQL)) {
            statement.setLong(1, weaponID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Weapon(
                            resultSet.getInt("weaponID"),
                            resultSet.getString("name"),
                            resultSet.getInt("damage"),
                            resultSet.getInt("cost")
                            );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveHeroWeapon(long heroID, int weaponID, int quantity) {
        Weapon weapon = getWeaponById(weaponID);
        if (weapon == null) {
            System.out.println("No owned weapons!");
            return false;
        }
        try (PreparedStatement statement = db.prepareStatement(INSERT_HERO_WEAPON_SQL)) {
            statement.setLong(1, heroID);
            statement.setInt(2, weaponID);
            statement.setInt(3, quantity);
            statement.setInt(4, weapon.getCost());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

