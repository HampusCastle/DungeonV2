package com.hampusborg.demo.database.repository;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDao {

    private DatabaseConnector db;

    public HistoryDao(DatabaseConnector db) {
       this.db = db;
    }

    public void fightHistory(Hero hero) throws SQLException {
        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT f.*, m.name AS monsterName, h.name AS heroName " +
                                "FROM Fight f " +
                                "JOIN Hero h ON f.heroID = h.heroID " +
                                "JOIN Monster m ON f.monsterID = m.monsterID " +
                                "WHERE h.heroID = ?")) {

            statement.setLong(1, hero.getHeroID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long fightID = resultSet.getLong("fightID");
                String monsterName = resultSet.getString("monsterName");
                String heroName = resultSet.getString("heroName");
                String winner = resultSet.getString("winner");

                System.out.println("Fight ID: " + fightID);
                System.out.println("Timestamp: " + resultSet.getTimestamp("timestamp"));
                System.out.println("Hero ID: " + resultSet.getLong("heroID"));
                System.out.println("Hero Name: " + heroName);

                System.out.println("Monster ID: " + resultSet.getLong("monsterID"));
                System.out.println("Monster Type: " + resultSet.getString("monsterType"));
                System.out.println("Winner: " + winner);
            }
        }
    }
}