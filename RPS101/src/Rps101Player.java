/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 2
 * Due Date: Oct. 6, 2024
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class Name: Rps101Player
 * Purpose: This class is designed to read the objects from the .csv file.
 *          The first column will read the winning object along with the losing object in
 *          the first row. The intersection of each object is the resulted winning verb.
 *          For example, The intersection between Air and Planet results in: "Air covers Planet".
 * @author Mack Bautista
 */
public class Rps101Player {
    private final static String FILE_NAME = "allOutcomes.csv";
    private final static String PLAYER_1 = "Player 1";
    private final static String PLAYER_2 = "Player 2";
    private static Scanner keyboard = new Scanner (System.in);


    /**
     * The main class is specifically only for running the program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Rps101Player player = new Rps101Player();
        player.run();
    }

    /**
     * Runs the program and function calls.
     */
    public void run() {
        System.out.println("--+-- Running Rps101Player.java... --+--");
        final List<String> allObjects = readAllObjects(FILE_NAME);
        final List<List<String>> allOutcomes = readAllOutcomes(FILE_NAME);

        System.out.println("\n+=-=+ Let's play RPS101! +=-=+");

        System.out.println(PLAYER_1 + ", what do you choose?");
        String player1Object = keyboard.next();
        checkValidObject(player1Object, PLAYER_1, allObjects);
        System.out.println();



        System.out.println(PLAYER_2 + ", what do you choose?");
        String player2Object = keyboard.next();
        checkValidObject(player2Object, PLAYER_2, allObjects);
        System.out.println();

        playRps101(player1Object, player2Object, allOutcomes);
    }

    /**
     *  Calls the user's choices for two payers and then describe the outcome of the match.
     * @param player1 chosen object
     * @param player2 chosen object
     * @param allOutcomes of all 101 RPS objects
     */
    public void playRps101(String player1, String player2, List<List<String>> allOutcomes) {

        String winnerMessage = null;

        for (List<String> outcomes : allOutcomes) {
            String objectName = outcomes.get(0);

            // Check if player1 chose this object (winning object)
            if (objectName.equalsIgnoreCase(player1)) {

                for (int i = 1; i < outcomes.size() - 1; i += 2) {
                    String winningVerb = outcomes.get(i);
                    String losingObject = outcomes.get(i + 1);



                    // If player2's choice matches the losing object, player1 wins
                    if (losingObject.equalsIgnoreCase(player2)) {
                        winnerMessage = "Player 1 wins: " + objectName + " " + winningVerb + " " + losingObject + ".";
                        break;
                    }
                }
            }

            // Check if player2 chose this object (winning object)
            if (objectName.equalsIgnoreCase(player2)) {

                for (int i = 1; i < outcomes.size() - 1; i += 2) {
                    String winningVerb = outcomes.get(i);
                    String losingObject = outcomes.get(i + 1);


                    // If player1's choice matches the losing object, player2 wins
                    if (losingObject.equalsIgnoreCase(player1)) {
                        winnerMessage = "Player 2 wins: " + objectName + " " + winningVerb + " " + losingObject + ".";
                        break;
                    }
                }
            }

            if (winnerMessage != null) {
                break;
            }
        }


        // Print the winner message or declare a draw
        if (winnerMessage != null) {
            System.out.println(winnerMessage);
        } else {
            System.out.println("It's a draw!");
        }
    }




    /**
     * Reads all objects from the .Yocsv file.
     * @param fileName allOutcomes.csv file
     */
    public List<String> readAllObjects(String fileName) {
        System.out.print("Reading all objects from '" + FILE_NAME + "'... ");
        List<String> allObjects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String row = reader.readLine();

            if (row != null) {
                String[] objects = row.split(",");
                allObjects.addAll(Arrays.asList(objects));
            }

        } catch (FileNotFoundException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to find file.");
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to read file.");
            System.exit(-1);
        }
        System.out.print(" done.");
        return allObjects;
    }

    /**
     * Reads all outcomes from the .csv file.
     * @param fileName allOutcomes.csv file
     */
    public List<List<String>> readAllOutcomes(String fileName) {
        System.out.print("\nReading all outcomes from '" + fileName + "'... ");
        List<List<String>> allOutcomes = new ArrayList<>();
        List<String> losingObjects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            if ((line = reader.readLine()) != null) {
                String[] objects = line.split(",");
                for (int i = 1; i < objects.length; i++) {
                    losingObjects.add(objects[i].trim());
                }
            }

            // Read the rest of the lines to get the winning outcomes for each object
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(",");

                String objectName = elements[0].trim();

                // Create a list to store the winning outcomes for the current object
                List<String> outcomeEntry = new ArrayList<>();
                outcomeEntry.add(objectName);

                // Loop through the rest of the elements (starting from index 1)
                for (int i = 1; i < elements.length; i++) {
                    String winningVerb = elements[i].trim().replace(";", ",");
                    if (!winningVerb.isEmpty()) {
                        // Add the winning verb and corresponding losing object
                        outcomeEntry.add(winningVerb);
                        outcomeEntry.add(losingObjects.get(i - 1));
                    }
                }

                // Add the entry to the list of all outcomes if it contains valid outcomes
                if (outcomeEntry.size() > 1) {
                    allOutcomes.add(outcomeEntry);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to find file.");
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to read file.");
            System.exit(-1);
        }

        System.out.print(" done.\n");
        return allOutcomes;
    }

    /**
     * Checks if the player chosen object exists in the game.
     * @param objectName the player's object
     * allObjects all the objects in RPS101.
     */
    public void checkValidObject(String objectName, String playerNumber, List<String> allObjects) {
        boolean isValid = false;

        for (String object : allObjects) {
            if (object.equalsIgnoreCase(objectName)) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            System.out.println("I'm sorry " + playerNumber + ", but '"
                    + objectName + "' is not a valid object in RPS-101.");
            System.exit(-1);
        }
    }



}
