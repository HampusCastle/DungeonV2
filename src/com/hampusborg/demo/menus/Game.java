package com.hampusborg.demo.menus;

import com.hampusborg.demo.heroes.AHero;
import com.hampusborg.demo.input.Input;
import com.hampusborg.demo.interfaces.IColors;
import com.hampusborg.demo.monsters.AMonster;

public class Game implements IColors {

    public boolean isPlaying = true;

    public Game() {
    }


    public void choice() {
        System.out.println("Hello and welcome to another spinoff of quality books/movies that never turns out great!\n Introducing.. Super cool options: ");
        do {


            System.out.println("1. Start new game");
            System.out.println("2. Learn more about why you're here and is completely accurate and cool (sooo cool..)");
            System.out.println("3. Exit the app and get on to better things and try to enjoy life..");
            switch (Input.getIntInput("")) {
                case 1 -> {
                    startGame();
                    isPlaying = false;
                }
                case 2 -> {
                    System.out.println(gameLore());
                    isPlaying = true;
                }
                case 3 -> exitGame();
                default -> System.out.println("At least try to make sense of things, try again..");
            }

        } while (isPlaying);
    }

    private void startGame() {

        LevelsMenu levelsMenu = new LevelsMenu();
        levelsMenu.start();


    }


    protected void exitGame() {
        isPlaying = false;
    }

    private String gameLore() {
        return "Oh, wow, just what the world needs – another spin-off of Lord of the Rings. Because clearly, the story of a magical ring and a bunch of people walking for three movies wasn't enough. I mean, who wouldn't want to revisit that groundbreaking narrative for the umpteenth time?\n" +
                "\n" +
                "And here I was, thinking that the Hobbit trilogy was the pinnacle of cinematic achievement. But no, we need more. We need more dwarves, elves, and hobbits doing whatever it is they do for three more hours. Because nothing says originality like milking a franchise until even the orcs are rolling their eyes.\n" +
                "\n" +
                "But wait, there's more! Let's not forget the riveting video game adaptation, because who wouldn't want to press buttons to make virtual characters reenact scenes we've seen a million times before? I can't contain my excitement at the prospect of pressing 'A' to throw the One Ring into Mount Doom for the hundredth time.\n" +
                "\n" +
                "And you, dear player, you're here for... what exactly? The groundbreaking gameplay? The revolutionary storytelling that will surely eclipse the original trilogy? Or maybe you're just a glutton for punishment, willingly subjecting yourself to the same recycled plotlines and characters, wondering if this time Frodo will take a shortcut through Mordor and save us all some time.\n" +
                "\n" +
                "But hey, who am I to question the infinite wisdom of the entertainment industry? Clearly, the geniuses behind these spin-offs know exactly what they're doing. I'm just a lowly mortal, incapable of appreciating the unparalleled brilliance of another journey through Middle-earth. So, go ahead, dive into that fresh, innovative take on Tolkien's universe. I'm sure it will be an experience like no other.";
    }
}
