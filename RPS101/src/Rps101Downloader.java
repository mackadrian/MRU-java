/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 2
 * Due Date: Oct. 6, 2024
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;


/**
 * Class Name: Rps101Downloader
 * Purpose: This class is designed to load JSON data from the RPS101 Web API.
 *
 * @author Mack Bautista
 */
public class Rps101Downloader {

    private final static String FILE_NAME = "allOutcomes.csv";
    private final static String ALL_OBJECTS_URL = "https://rps101.pythonanywhere.com/api/v1/objects/all";

    /**
     * The main class is specifically done for function calling.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Rps101Downloader downloader = new Rps101Downloader();
        downloader.run();
    }

    /**
     * Runs the program and function calls.
     */
    public void run() {
        System.out.print("Getting all objects... ");
        List<String> allObjects = getAllObjects();
        System.out.print(" done.");
        //System.out.println("\n" + objects);

        List<List<String>> allWinningOutcomes = new ArrayList<>();
        ObjectWins wins = new ObjectWins();
        System.out.print("\nGetting all outcomes... ");
        for (String objectName : allObjects) {
            wins = getObjectOutcomes(objectName);
            allWinningOutcomes.addAll(wins.winningOutcomes); // Collect the outcomes
        }
        System.out.print(" done.");


        System.out.print("\nWriting all outcomes to '" + FILE_NAME + "'...");
        writeToCsv(FILE_NAME, allObjects, allWinningOutcomes);
        System.out.print(" done.");

    }





    /**
     * Reads all objects from the Web API.
     * @return ArrayList of all objects in RPS101
     */
    public List<String> getAllObjects() {
        List<String> allObjects = null;
        try (Reader reader = new InputStreamReader(new URI(ALL_OBJECTS_URL).toURL().openStream()))
        {
            Gson gson = new Gson();
            Type listType = TypeToken.getParameterized(List.class, String.class).getType();
            allObjects = gson.fromJson(reader, listType);

            /* Prints all objects
             * allObjects.forEach(System.out::println);
            */
        } catch (IOException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to read Web API.");
            System.exit(-1);
        } catch (URISyntaxException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to parse URL.");
            System.exit(-1);
        }

        if (allObjects == null) {
            System.out.print(" EXITING PROGRAM -- Objects is empty.");
            System.exit(-1);
        }
        return allObjects;

    }

    /**
     *
     * Reads all outcomes of one object from the Web API.
     * @param objectName name of one object in RPS101
     * @return all of the winning outcomes for the given object
     */
    public ObjectWins getObjectOutcomes(String objectName) {
        final String objectUrl = String.format(
            "https://rps101.pythonanywhere.com/api/v1/objects/%s",
            objectName.replace(" ", "%20"));
        ObjectWins wins = null;
        try (Reader reader = new InputStreamReader(new URI(objectUrl).toURL().openStream())) {
            Gson gson = new Gson();
            wins = gson.fromJson(reader, ObjectWins.class);

            /* Prints all of winning outcomes for each object.
            if (wins != null && wins.winningOutcomes != null) {
                System.out.println("\nWinning outcomes for " + objectName + ":");
                for (List<String> outcomeList : wins.winningOutcomes) {
                    System.out.println(outcomeList);
                }
            }
            */


        } catch (IOException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to read Web API.");
            System.exit(-1);
        } catch (URISyntaxException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to parse URL.");
            System.exit(-1);
        }
        return wins;
    }

    /**
     * Writes to a .csv file.
     * @param fileName file name that is written to.
     */
    public void writeToCsv(String fileName, List<String> allObjects, List<List<String>> allOutcomes) {
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Writes the top row with all 50 objects.
            writer.write("," + String.join(",", allObjects));
            writer.newLine();


            // Iterate over each object for the rows
            for (int i = 0; i < allObjects.size(); i++) {
                String objectName = allObjects.get(i);
                StringBuilder row = new StringBuilder(objectName);

                List<String> verbs = getVerbOutcomes(allOutcomes);
                for (int j = 0; j < allObjects.size(); j++) {
                    if (objectName == verbs.get(j)) {
                        row.append(",");
                    }

                }
                writer.write(row.toString());
                writer.newLine();

            }
            // Write the complete row to the CSV
            writer.close();

        } catch (IOException ex) {
            System.out.println(" EXITING PROGRAM -- Unable to write to file.");
            System.exit(-1);
        }

    }

    private static List<String> getVerbOutcomes (List<List<String>> allOutcomes) {
        List<String> verbs = new ArrayList<String>();

        for (List<String> eachOutcome : allOutcomes) {
            for (String description : eachOutcome) {
                verbs.add(description);
            }
        }

        return verbs;
    }



    private static class ObjectWins {
        @SerializedName("winning outcomes")
        public List<List<String>> winningOutcomes;
    }




}
