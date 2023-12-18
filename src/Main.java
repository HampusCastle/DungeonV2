import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.menus.Game;
import com.hampusborg.demo.menus.HeroMenu;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.MonsterFactory;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        DatabaseConnector db = new DatabaseConnector();
        db.openConnection();
        try {
            MonsterFactory monsterFactory = new MonsterFactory();
            Game game = new Game();
            game.choice();
            Hero hero = new HeroMenu().choosePlayer();
            LevelsMenu levelsMenu = new LevelsMenu(hero);
            monsterFactory.spawnMonster();
            levelsMenu.startCombat();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}