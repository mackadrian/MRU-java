package classList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * InStringFile makes file reading simpler. It allows
 * information to be read one line at a time from a
 * data file, as a String.
 *
 * @author Bita Sadeghi
 */
public class InStringFile {

    /**
     * The handle to read in the file.
     */
    private BufferedReader in;

    /**
      * The next line of the file to be read.
      */
    private String nextLine;

    /**
     * Constructs the object that controls file reading.
     * Exits gracefully if file not found or file cannot be read.
     *
     * @param filename the name of the file to be read
     */
    public InStringFile(String filename) {
        try {
            in = new BufferedReader(new FileReader(filename));
            nextLine = in.readLine();
        } catch (FileNotFoundException ex) {
            System.out.println("File " + filename + " not found.");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("File " + filename + " cannot be read.");
            System.exit(0);
        }
    }

    /**
     * Reads the next line of input as a String.
     * Exits gracefully if an error occurs while reading the file.
     *
     * @return the next line from the input file
     */
    public String read() {
        String current = nextLine;
        try {
            nextLine = in.readLine();
        } catch (IOException ex) {
            System.out.println("File cannot be read.");
            System.exit(0);
        }
        return current;
    }

    /**
     * Lookahead test for end of input.
     * @return true if end of file has been reached
     */
    public boolean endOfFile() {
        return (nextLine == null);
    }

    /**
     * Closes the file (making it inaccessible though this InStringFile).
     */
    public void close() {
        try {
            in.close();
            in = null;
        }
        catch (IOException ex) {
            System.out.println("Problem closing file.");
            System.exit(0);
        }
    }
}