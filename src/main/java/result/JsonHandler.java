package result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.tinylog.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class containing methods used to write to and read from
 * the scores.json file in the project directory.
 */
public class JsonHandler {

    /**
     * Writes the game scores to the scores.json file
     * in the project directory.
     *
     * @param result the result object to write to the file
     */
    public void write(final Result result) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Result> results = new ArrayList<>();

        if (read() != null) {
            results = read();
        }

        results.add(result);

        try {
            FileWriter writer = new FileWriter("scores.json");
            String s = gson.toJson(results);
            writer.write(s);
            writer.flush();
            writer.close();
            Logger.info("Successfully wrote the new score to scores.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the scores.json file found in the project directory
     * and loads the contents into an array of Result objects.
     *
     * @return an array containing the objects from the scores.json file
     */
    public ArrayList read() {

        ArrayList<Result> results = new ArrayList<>();

        try {
            FileReader reader = new FileReader("scores.json");

            try {
                results = new Gson().fromJson(
                        new FileReader("scores.json"),
                        ArrayList.class);
            } catch (Exception e) {
                Logger.error("There was a problem with reading the "
                        + "file scores.json. Deleting contents...");
                delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Empties the scores.json file.
     */
    public void delete() {
        try {
            FileWriter writer = new FileWriter("scores.json");
            writer.write("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
