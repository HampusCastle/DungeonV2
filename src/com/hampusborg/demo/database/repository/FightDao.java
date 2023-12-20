package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.menus.Fight;
import com.hampusborg.demo.monsters.AMonster;
import org.mariadb.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FightDao {
    private static final String INSERT_COMBATLOG_SQL = "INSERT INTO Combatlog (timestamp, heroID, heroName, heroInitialHealth, heroHealthLost, monsterID, monsterType, monsterInitialHealth, monsterHealthLost, battleWinner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public boolean saveFightToDatabase(Fight fight, Hero hero, AMonster monster, DatabaseConnector tempDB) {

        try (Connection db = tempDB.getConnection();
             PreparedStatement statement = db.prepareStatement(INSERT_COMBATLOG_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setTimestamp(1, new java.sql.Timestamp(fight.getTimestamp().getTime()));
            statement.setLong(2, fight.getHeroID());
            statement.setString(3, hero.getName());
            statement.setInt(4, fight.getHeroInitialHealth());
            statement.setInt(5, fight.getHeroHealthLost());
            statement.setLong(6, fight.getMonsterID());
            statement.setString(7, monster.getName());
            statement.setInt(8, fight.getMonsterInitialHealth());
            statement.setInt(9, fight.getMonsterHealthLost() + fight.getDamageDealt());
            statement.setString(10, fight.getWinner());


            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert fight");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fight.setFightID(generatedKeys.getLong(1));
                    return true;
                } else {
                    throw new SQLException("Failed to retrieve a auto-generated key");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

}
