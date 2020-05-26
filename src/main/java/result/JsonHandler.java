package result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.tinylog.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

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
        Gson gson = new GsonBuilder().create();

        try {
            results = gson.fromJson(
                    new FileReader("scores.json"),
                    ArrayList.class);
        } catch (Exception e) {
            Logger.error("There was a problem with reading the "
                    + "file scores.json. Deleting contents...");
            delete();
        }
        return results;
    }

    /**
     * Reads the scores.json file into a {@code Result[]} array.
     *
     * @return a {@code Result[]} array containing objects described by
     *      the scores.json file
     * @throws IOException if an I/O problem occurs
     */
    public static Result[] readGsonArray() throws IOException {

        Gson gson = new GsonBuilder().create();

        String fileName = "scores.json";
        Path path = new File(fileName).toPath();

        try (Reader reader = Files.newBufferedReader(path,
                StandardCharsets.UTF_8)) {

            Result[] results = gson.fromJson(reader, Result[].class);

            Arrays.stream(results).forEach(System.out::println);

            return results;

        }
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
