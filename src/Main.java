
import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.menus.Game;
import com.hampusborg.demo.menus.HeroMenu;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.AMonster;
import com.hampusborg.demo.monsters.MonsterFactory;


public class Main {
    public static void main(String[] args) {


        Game game = new Game();
        game.choice();
        LevelsMenu levelsMenu = new LevelsMenu();
        AHero hero = new HeroMenu().choosePlayer();
        AMonster monster = MonsterFactory.spawnMonster();
    levelsMenu.startCombat(hero);






    }
}