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
 * Purpose: This class is designed to load JSON data from the RPS101 Web API. All outcomes
 *          will get written to a .csv file.
 *
 * @author Mack Bautista
 */
public class Rps101Downloader {

    private final static String FILE_NAME = "allOutcomes.csv";
    private final static String ALL_OBJECTS_URL = "https://rps101.pythonanywhere.com/api/v1/objects/all";

    /**
     * The main class is specifically only for running the program.
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
        System.out.println("--+-- Running Rps101Downloader.java... --+--");
        List<String> allObjects = getAllObjects();
        getObjectOutcomes(allObjects);
    }

    /**
     * Reads all objects from the Web API.
     * @return ArrayList of all objects in RPS101
     */
    public List<String> getAllObjects() {
        System.out.print("Getting all objects... ");
        List<String> allObjects = null;
        try (Reader reader = new InputStreamReader(new URI(ALL_OBJECTS_URL).toURL().openStream()))
        {
            Gson gson = new Gson();
            Type listType = TypeToken.getParameterized(List.class, String.class).getType();
            allObjects = gson.fromJson(reader, listType);

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
        System.out.print(" done.");
        return allObjects;

    }

    /**
     *
     * Reads all outcomes of one object from the Web API.
     * @param allObjects in RPS101
     *
     */
    public void getObjectOutcomes(List<String> allObjects) {
        System.out.print("\nGetting all outcomes... ");
        ObjectWins wins = null;

        for (String objectName : allObjects) {
            final String objectUrl = String.format(
                    "https://rps101.pythonanywhere.com/api/v1/objects/%s",
                    objectName.replace(" ", "%20"));

            try (Reader reader = new InputStreamReader(new URI(objectUrl).toURL().openStream())) {
                Gson gson = new Gson();
                wins = gson.fromJson(reader, ObjectWins.class);

                if (wins != null && wins.winningOutcomes != null) {
                    writeToCsv(FILE_NAME, objectName, wins.winningOutcomes, allObjects);
                }

            } catch (IOException ex) {
                System.out.println(" EXITING PROGRAM -- Unable to read Web API.");
                System.exit(-1);
            } catch (URISyntaxException ex) {
                System.out.println(" EXITING PROGRAM -- Unable to parse URL.");
                System.exit(-1);
            }
        }
        System.out.print(" done.");
        System.out.print("\nWriting all outcomes to '" + FILE_NAME + "'... done.");
    }

    /**
     * Writes to a .csv file.
     * @param fileName file name that is written to.
     */
    public void writeToCsv(String fileName, String objectName,
            List<List<String>> winningOutcomes, List<String> allObjects) {
        File file = new File(fileName);

        List<List<String>> csvData = new ArrayList<>();
        boolean fileExists = file.exists();

        if (!fileExists) {
            List<String> headerRow = new ArrayList<>();
            headerRow.add(""); // First row and column is empty
            headerRow.addAll(allObjects);
            csvData.add(headerRow);
        }

        List<String> currentRow = new ArrayList<>();
        currentRow.add(objectName);

        // Tracks the winning verbs for each losing object
        for (String opponent : allObjects) {
            String winningVerb = "";
            for (List<String> outcomeList : winningOutcomes) {
                if (outcomeList.get(1).equals(opponent)) {
                    winningVerb = outcomeList.get(0);
                    winningVerb = winningVerb.replace(",", ";");
                    break;
                }
            }
            currentRow.add(winningVerb);
        }
        csvData.add(currentRow);

        // Write the collected data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (List<String> row : csvData) {
                writer.write(String.join(",", row) + "\n");
            }
        } catch (IOException ex) {
            System.out.println("EXITING PROGRAM -- Unable to write to file.");
            System.exit(-1);
        }
    }


    private static class ObjectWins {
        @SerializedName("winning outcomes")
        public List<List<String>> winningOutcomes;
    }




}
