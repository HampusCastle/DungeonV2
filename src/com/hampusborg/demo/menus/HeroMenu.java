package com.hampusborg.demo.menus;
import com.hampusborg.demo.heroes.*;
import com.hampusborg.demo.input.Input;

public class HeroMenu {
    public HeroMenu() {
    }



    public AHero choosePlayer() {

        System.out.println("Choose class: \n 1. - Ranger \n 2. - Warrior \n 3. - Wizard \n 4. - Dwarf");

        switch (Input.getIntInput("")) {
            case 1 -> {
                System.out.println("The ranger, well shooting people from a distance seems like something you would choose. You don't really look like a physical combat type for person..");
                return new Ranger();
            }
            case 2 -> {
                System.out.println("The warrior, I have to give it to you, if I had to guess I wouldn't say you look like a brute type of person.");
                return new Warrior();
            }
            case 3 -> {
                System.out.println("The Wizard, everyone wants to be like Gandalf but end up looking like Harry Potter if he didn't have no magical powers. But hey, you might be different.");
                return new Wizard();
            }
            case 4 -> {
                System.out.println("Swinging axes and emptying kegs. Well, to be fair, if I was a dwarf living in Moria. Look it up.");
                return new Dwarf();
            }

            default -> {
                System.out.println("Faulty input, give it another try, and put some effort in for the love of god..");
                return choosePlayer();
            }
        }
    }
}

