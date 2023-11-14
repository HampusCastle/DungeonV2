package com.hampusborg.demo.input;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        int input = 0;
        boolean validInput = false;
        do {
            try {
                System.out.println(prompt);
                input = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number");
            }

        } while (!validInput);
        return input;

    }


}
