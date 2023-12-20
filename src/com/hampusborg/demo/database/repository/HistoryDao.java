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
                        "SELECT cl.*, m.name AS monsterName, h.name AS heroName " +
                                "FROM CombatLog cl " +
                                "JOIN Hero h ON cl.heroID = h.heroID " +
                                "JOIN Monster m ON cl.monsterID = m.monsterID " +
                                "WHERE h.heroID = ?")) {

            statement.setLong(1, hero.getHeroID());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long fightID = resultSet.getLong("fightID");
                String monsterName = resultSet.getString("monsterName");
                String heroName = resultSet.getString("heroName");
                String winner = resultSet.getString("battleWinner");

                System.out.println("Fight ID: " + fightID +
                        "\n ___________" + "Timestamp: " + resultSet.getTimestamp("timestamp") +
                        "\n ___________" + "Hero ID: " + resultSet.getLong("heroID") +
                                "\n ___________" + "Hero Name: " + heroName +
                                "\n ___________" + "Monster ID: " + resultSet.getLong("monsterID") +
                                "\n ___________" + "Monster Type: " + resultSet.getString("monsterType") +
                                "\n ___________" + "Winner: " + winner);

            }
        }
    }
}