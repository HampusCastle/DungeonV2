
import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.menus.Game;
import com.hampusborg.demo.menus.HeroMenu;
import com.hampusborg.demo.menus.LevelsMenu;
import com.hampusborg.demo.monsters.MonsterFactory;
import com.hampusborg.demo.shop.Weapon;


import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        ArrayList<Weapon> weapons = new ArrayList<>();
        MonsterFactory monsterFactory = new MonsterFactory();
        Game game = new Game();
        game.choice();
        LevelsMenu levelsMenu = new LevelsMenu();
        AHero hero = new HeroMenu().choosePlayer();
        monsterFactory.spawnMonster();
        levelsMenu.startCombat(hero);
    }
}