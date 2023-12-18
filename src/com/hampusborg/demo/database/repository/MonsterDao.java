package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.monsters.AMonster;
import org.mariadb.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MonsterDao {
    private Connection db;
    private static final String INSERT_MONSTER_SQL = "INSERT INTO Monster (name, damage, health) VALUES (?, ?, ?)";


    public Long saveMonsterToDatabase(AMonster monster, DatabaseConnector tempDB) throws SQLException {
        try (Connection db = tempDB.getConnection();
             PreparedStatement statement = db.prepareStatement(INSERT_MONSTER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, monster.getName());
            statement.setInt(2, monster.getDamage());
            statement.setInt(3, monster.getHealth());


            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert Monster");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long monsterID = generatedKeys.getLong(1);
                    monster.setMonsterID(monsterID);
                    return monsterID;
                } else {
                    throw new SQLException("Failed to retrieve auto-generated key for monsterID");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}



