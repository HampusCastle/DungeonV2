package com.hampusborg.demo.menus;

import com.hampusborg.demo.database.DatabaseConnector;
import com.hampusborg.demo.heroes.Hero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;


public class Game implements IColors {

    public boolean isPlaying = true;
    private LevelsMenu levelsMenu;
    private Hero hero;



    private DatabaseConnector db;



    public void choice() {

        this.levelsMenu = new LevelsMenu(hero);


        this.db = new DatabaseConnector();


        System.out.println("Hello and welcome to another spinoff of quality books/movies that never turns out great!\n Introducing.. Super cool options: ");
        do {


            System.out.println(GREEN + "1. Start new game" + RESET);
            System.out.println(GREEN +"2. Load an saved player" + RESET);
            System.out.println(GREEN +"3. Learn more about why you're here and is completely accurate and cool (sooo cool..)"+ RESET);
            System.out.println(GREEN +"4. Exit the app and get on to better things and try to enjoy life.."+ RESET);
            switch (Input.getIntInput("")) {
                case 1 -> {
                    isPlaying = false;
                }
                case 2 -> {
                    db.openConnection();
                    levelsMenu = new LevelsMenu(hero);
                    levelsMenu.loadPlayer();
                    isPlaying = false;
                }
                case 3 -> {
                    System.out.println(gameLore());
                    isPlaying = true;
                }
                case 4 -> exitGame(null);

                default -> System.out.println("At least try to make sense of things, try again..");
            }

        } while (isPlaying);
    }


    protected void exitGame(Hero hero) {
        isPlaying = false;
        System.out.println(RED + "Bye bye now, on to better things." + RESET);
        System.exit(0);
    }

    private String gameLore() {
        return BLUE + "Oh, wow, just what the world needs – another spin-off of Lord of the Rings.\n Because clearly, the story of a magical ring and a bunch of people walking for three movies wasn't enough.\n I mean, who wouldn't want to revisit that groundbreaking narrative for the umpteenth time?\n" +
                "\n" +
                "And here I was, thinking that the Hobbit trilogy was the pinnacle of cinematic achievement. But no, we need more.\n We need more dwarves, elves, and hobbits doing whatever it is they do for three more hours.\n Because nothing says originality like milking a franchise until even the orcs are rolling their eyes.\n" +
                "\n" +
                "But wait, there's more! Let's not forget the riveting video game adaptation,\n because who wouldn't want to press buttons to make virtual characters reenact scenes we've seen a million times before?\n I can't contain my excitement at the prospect of pressing 'A' to throw the One Ring into Mount Doom for the hundredth time.\n" +
                "\n" +
                "And you, dear player, you're here for... what exactly?\n The groundbreaking gameplay?\n The revolutionary storytelling that will surely eclipse the original trilogy?\n Or maybe you're just a glutton for punishment, willingly subjecting yourself to the same recycled plotlines and characters,\n wondering if this time Frodo will take a shortcut through Mordor and save us all some time.\n" +
                "\n" +
                "But hey, who am I to question the infinite wisdom of the entertainment industry? Clearly, the geniuses behind these spin-offs know exactly what they're doing. \n I'm just a lowly mortal, incapable of appreciating the unparalleled brilliance of another journey through Middle-earth.\n So, go ahead, dive into that fresh, innovative take on Tolkien's universe. I'm sure it will be an experience like no other.\n" + RESET;
    }
}


