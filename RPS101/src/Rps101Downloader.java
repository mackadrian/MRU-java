/**
 * Name: Mack Bautista
 * Email: mbaut981@mtroyal.ca
 * Course: COMP2631-001
 * Instructor: Jason Heard
 * Assignment: 2
 * Due Date: Oct. 6, 2024
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
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



    /**
     * The main class is specifically done for function calling.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Rps101Downloader downloader = new Rps101Downloader();
        downloader.run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        getAllObjects();

    }




    /**
     * Reads all objects from the Web API.
     */
    public void getAllObjects() {
        final String allObjectsUrl = "https://rps101.pythonanywhere.com/api/v1/objects/all";
        System.out.print("Getting all objects... ");
        List<String> allObjects = null;
        try (Reader reader = new InputStreamReader(new URI(allObjectsUrl).toURL().openStream()))
        {
            Gson gson = new Gson();
            Type listType = TypeToken.getParameterized(List.class, String.class).getType();
            allObjects = gson.fromJson(reader, listType);
            System.out.print(" done.");

            //allObjects.forEach(System.out::println);

            if (allObjects != null && !allObjects.isEmpty()) {
                System.out.print("\nGetting all outcomes... ");
                for (String objectName : allObjects) {
                    getAllOutcomes(objectName);
                }
                System.out.print(" done.");
            }
            else {
                System.out.println("STOPPED -- Error: There are no objects to be read.");
                System.exit(-1);
            }


        } catch (IOException ex) {
            System.out.println(" -- Unable to read Web API.");
            System.exit(-1);
        } catch (URISyntaxException ex) {
            System.out.println(" -- Unable to parse URL.");
            System.exit(-1);
        }

    }

    /**
     *
     * Reads all outcomes of one object from the Web API.
     */
    public void getAllOutcomes(String objectName) {
        final String objectUrl = String.format(
            "https://rps101.pythonanywhere.com/api/v1/objects/%s",
            objectName.replace(" ", "%20"));
        ObjectWins wins = null;
        try (Reader reader = new InputStreamReader(new URI(objectUrl).toURL().openStream())) {
            Gson gson = new Gson();
            wins = gson.fromJson(reader, ObjectWins.class);


            /* Prints out the outer array of all outcomes.
             *
            if (wins != null && wins.winningOutcomes != null) {
                System.out.println("\nWinning outcomes for " + objectName + ":");
                for (List<String> outcomeList : wins.winningOutcomes) {
                    System.out.println(outcomeList);
                }
            }
            */

        } catch (IOException ex) {
            System.out.println(" -- Unable to read Web API.");
            System.exit(-1);
        } catch (URISyntaxException ex) {
            System.out.println(" -- Unable to parse URL.");
            System.exit(-1);
        }
    }

    private static class ObjectWins {
        @SerializedName("winning outcomes")
        public List<List<String>> winningOutcomes;
    }




}
