package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.shop.Weapon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HeroDao {

    private Connection db;
    private static final String INSERT_HERO_SQL = "INSERT INTO Hero (name, heroClass, strength, intelligence, agility, health, experience, gold, heroLevel, monstersKilled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_HERO_SQL = "UPDATE Hero SET name = ?, heroClass = ?, strength = ?, intelligence = ?, agility = ?, health = ?, experience = ?, gold = ?, heroLevel = ?, monstersKilled = ? WHERE id = ?";

    public boolean saveHeroToDatabase(Hero hero, DatabaseConnector tempDB) {
        this.db = tempDB.getConnection();
        try  {
            if (hero.getHeroID() == null) {
                PreparedStatement statement = db.prepareStatement(INSERT_HERO_SQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getStrength());
                statement.setInt(4, hero.getIntelligence());
                statement.setInt(5, hero.getAgility());
                statement.setInt(6, hero.getHealth());
                statement.setInt(7, hero.getExperience());
                statement.setInt(8, hero.getGold());
                statement.setInt(9, hero.getLevel());
                statement.setInt(10, hero.getMonstersKilled());
                //statement.setString(11, hero.getWeapon().getName());

                int rowsInserted = statement.executeUpdate();

                return rowsInserted > 0;

            } else {
                PreparedStatement statement = db.prepareStatement(UPDATE_HERO_SQL);
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getStrength());
                statement.setInt(4, hero.getIntelligence());
                statement.setInt(5, hero.getAgility());
                statement.setInt(6, hero.getHealth());
                statement.setInt(7, hero.getExperience());
                statement.setInt(8, hero.getGold());
                statement.setInt(9, hero.getLevel());
                statement.setInt(10, hero.getMonstersKilled());
                statement.setString(11, hero.getWeapon().getName());

                int rowsInserted = statement.executeUpdate();

                return rowsInserted > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Hero> showHeroes(Hero hero, DatabaseConnector tempDB) {
        this.db = tempDB.getConnection();

        List<Hero> savedHeroList = new ArrayList<>();

        try {
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Hero");
            ResultSet resultSet = statement.executeQuery();
            {
                while (resultSet.next()) {
                    hero.setHeroID(resultSet.getLong("heroID"));
                    hero.setName(resultSet.getString("name"));
                    hero.setHeroClass(resultSet.getString("heroClass"));
                    hero.setStrength(resultSet.getInt("strength"));
                    hero.setIntelligence(resultSet.getInt("intelligence"));
                    hero.setAgility(resultSet.getInt("agility"));
                    hero.setHealth(resultSet.getInt("health"));
                    hero.setExperience(resultSet.getInt("experience"));
                    hero.setGold(resultSet.getInt("gold"));
                    hero.setLevel(resultSet.getInt("heroLevel"));
                    hero.setMonstersKilled(resultSet.getInt("monstersKilled"));

                    Weapon weapon = new Weapon();
                    weapon.setName(resultSet.getString("weaponName"));


                    hero.addToInventory(weapon);
                    savedHeroList.add(hero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedHeroList;
    }

    public Hero findHeroId(long id, DatabaseConnector tempDB, Hero hero) {
        this.db = tempDB.getConnection();

        try {
            PreparedStatement statement = db.prepareStatement("SELECT * FROM Hero WHERE heroID = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                hero.setHeroID(resultSet.getLong("heroID"));
                hero.setName(resultSet.getString("name"));
                hero.setHeroClass(resultSet.getString("heroClass"));
                hero.setStrength(resultSet.getInt("strength"));
                hero.setIntelligence(resultSet.getInt("intelligence"));
                hero.setAgility(resultSet.getInt("agility"));
                hero.setHealth(resultSet.getInt("health"));
                hero.setExperience(resultSet.getInt("experience"));
                hero.setGold(resultSet.getInt("gold"));
                hero.setLevel(resultSet.getInt("heroLevel"));
                hero.setMonstersKilled(resultSet.getInt("monstersKilled"));

                Weapon weapon = new Weapon();
                weapon.setName(resultSet.getString("weaponName"));


                hero.setWeapon(weapon);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }
}


