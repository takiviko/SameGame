package result;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonHandler {

    public void write(Result result) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Result> results = new ArrayList<>();

        if(read() != null) {
            results = read();
            Logger.trace("Read from file: \n" + results);
        }

        results.add(result);
        String stringToWrite = gson.toJson(results);

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("JSON/scores.json");
        try {
            File file = new File(url.toURI());
            Files.writeString(Paths.get(file.toString()), stringToWrite);
            Logger.trace("Wrote to file: " + stringToWrite);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList read() {

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("JSON/scores.json");

        ArrayList<Result> results = new ArrayList<>();

        try {
            File f = new File(url.toURI());
            results = new Gson().fromJson(Files.readString(Paths.get(f.toString())), ArrayList.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void delete() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("JSON/scores.json");

        try {
            File f = new File(url.toURI());
            Files.writeString(Paths.get(f.toString()), "");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

    }

}
