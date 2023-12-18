package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HeroDao {
    private static final String INSERT_HERO_SQL = "INSERT INTO Hero (name, heroClass, damage, strength, intelligence, agility, health, experience, gold, heroLevel, monstersKilled, CreationTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_HERO_SQL = "UPDATE Hero SET name = ?, heroClass = ?, damage = ?, strength = ?, intelligence = ?, agility = ?, health = ?, experience = ?, gold = ?, heroLevel = ?, monstersKilled = ? WHERE heroID = ?";

    public boolean saveHeroToDatabase(Hero hero, DatabaseConnector tempDB) {

        try (Connection db = tempDB.getConnection()) {
            if (hero.getHeroID() == null || hero.getHeroID() == 0) {
                try (PreparedStatement statement = db.prepareStatement(INSERT_HERO_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, hero.getName());
                    statement.setString(2, hero.getHeroClass());
                    statement.setInt(3, hero.getDamage());
                    statement.setInt(4, hero.getStrength());
                    statement.setInt(5, hero.getIntelligence());
                    statement.setInt(6, hero.getAgility());
                    statement.setInt(7, hero.getHealth());
                    statement.setInt(8, hero.getExperience());
                    statement.setInt(9, hero.getGold());
                    statement.setInt(10, hero.getLevel());
                    statement.setInt(11, hero.getMonstersKilled());
                    statement.setTimestamp(12, java.sql.Timestamp.from(java.time.Instant.now()));


                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                hero.setHeroID(generatedKeys.getLong(1));
                            } else {
                                throw new SQLException("Failed to retrieve auto-generated key");
                            }
                        }
                        return true;
                    }
                }
            } else {
                try (PreparedStatement statement = db.prepareStatement(UPDATE_HERO_SQL)) {
                    statement.setString(1, hero.getName());
                    statement.setString(2, hero.getHeroClass());
                    statement.setInt(3, hero.getDamage());
                    statement.setInt(4, hero.getStrength());
                    statement.setInt(5, hero.getIntelligence());
                    statement.setInt(6, hero.getAgility());
                    statement.setInt(7, hero.getHealth());
                    statement.setInt(8, hero.getExperience());
                    statement.setInt(9, hero.getGold());
                    statement.setInt(10, hero.getLevel());
                    statement.setInt(11, hero.getMonstersKilled());
                    statement.setLong(12, hero.getHeroID());


                    int rowsInserted = statement.executeUpdate();
                    return rowsInserted > 0;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    public List<Hero> showHeroes() {

        List<Hero> savedHeroList = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection()) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM Hero");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Hero hero = new Hero();
                    hero.setHeroID(resultSet.getLong("heroID"));
                    hero.setName(resultSet.getString("name"));
                    hero.setHeroClass(resultSet.getString("heroClass"));
                    hero.setDamage(resultSet.getInt("damage"));
                    hero.setStrength(resultSet.getInt("strength"));
                    hero.setIntelligence(resultSet.getInt("intelligence"));
                    hero.setAgility(resultSet.getInt("agility"));
                    hero.setHealth(resultSet.getInt("health"));
                    hero.setExperience(resultSet.getInt("experience"));
                    hero.setGold(resultSet.getInt("gold"));
                    hero.setLevel(resultSet.getInt("heroLevel"));
                    hero.setMonstersKilled(resultSet.getInt("monstersKilled"));

                    savedHeroList.add(hero);
                }
            } else {
                System.out.println("Failed to establish connections to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedHeroList;
    }

    public Hero findHeroId(long id) {
        Hero hero = new Hero();
        Connection conn = null;

        try {
            conn = DatabaseConnector.getConnection();
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM Hero WHERE heroID = ?");
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    hero.setHeroID(resultSet.getLong("heroID"));
                    hero.setName(resultSet.getString("name"));
                    hero.setHeroClass(resultSet.getString("heroClass"));
                    hero.setDamage(resultSet.getInt("damage"));
                    hero.setStrength(resultSet.getInt("strength"));
                    hero.setIntelligence(resultSet.getInt("intelligence"));
                    hero.setAgility(resultSet.getInt("agility"));
                    hero.setHealth(resultSet.getInt("health"));
                    hero.setExperience(resultSet.getInt("experience"));
                    hero.setGold(resultSet.getInt("gold"));
                    hero.setLevel(resultSet.getInt("heroLevel"));
                    hero.setMonstersKilled(resultSet.getInt("monstersKilled"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return hero;
    }

}



