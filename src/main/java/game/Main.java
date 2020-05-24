package game;

import result.JsonHandler;
import result.Result;

import java.security.InvalidParameterException;
import org.tinylog.Logger;

/**
 * The main class used by the game.
 */
public final class Main {

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * The entry point to the game.
     *
     * @param args the arguments passed to the method
     *      no arguments:   run in GUI mode
     *      -headless:      run in headless mode
     */
    public static void main(final String[] args) {
        run(args[0]);
    }

    private static void run(final String mode) {

        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();

        if (mode.equals("")) {
            Logger.info("Running in GUI mode.");
            //TODO Run the gui
        } else if (mode.equals("-headless")) {
            Logger.info("Running in headless mode.");
            result = headless.HeadlessRunner.run();
        } else {
            Logger.error("Invalid parameter! In case you'd like to run the "
                    + "application in headless mode use the -headless flag.");
            throw new InvalidParameterException();
        }
        jsonHandler.write(result);
    }
}
