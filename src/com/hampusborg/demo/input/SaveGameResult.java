


// TODO - Detta är en ScoreFile, utkommenterad för att inte skrivas ut efter spelet är spelat, för att det istället
// TODO - för att det istället ska vara databasen.

/*
package com.hampusborg.demo.input;
import com.hampusborg.demo.heroes.Hero;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;


    public class SaveGameResult {
        public static void saveToFileAndOpen(Hero hero, String filePath) {
            String fileContent = hero.toSaveFileString();

            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(fileContent);
                System.out.println("Game results saved to: " + filePath);

                openFile(filePath);
            } catch (IOException e) {
                System.err.println("Error saving game results to file: " + e.getMessage());
            }
        }

        private static void openFile(String filePath) {
            try {
                File file = new File(filePath);
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } catch (IOException e) {
                System.err.println("Error opening the file: " + e.getMessage());
            }
        }
    }
*/

