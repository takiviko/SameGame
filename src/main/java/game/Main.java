package game;

import headless.HeadlessRunner;
import result.JsonHandler;
import result.Result;

import java.security.InvalidParameterException;
import org.tinylog.Logger;

public class Main {
    public static void main(String[] args) {
        run(args[0]);
    }

    private static void run(String mode) {

        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();

        if(mode.equals("")) {
            Logger.info("Running in GUI mode.");
            //TODO Run the gui
        } else if(mode.equals("-headless")) {
            Logger.info("Running in headless mode.");
            result = headless.HeadlessRunner.run();
        } else {
            Logger.error("Invalid parameter! In case you'd like to run the application in headless mode use the -headless flag.");
            throw new InvalidParameterException();
        }
        jsonHandler.write(result);
    }
}