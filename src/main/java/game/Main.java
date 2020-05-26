package game;

import javaFX.SameGameApplication;
import result.JsonHandler;
import result.Result;

import org.tinylog.Logger;
import javafx.application.Application;

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
        run(args);
    }

    private static void run(final String[] mode) {

        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();

        if (mode.length == 0) {

            Logger.info("Running in GUI mode.");
            Application.launch(javaFX.SameGameApplication.class);

            if (result.getMoves() > 0) {
                jsonHandler.write(result);
            }

        } else if (mode[0].equals("-headless")) {

            Logger.info("Running in headless mode.");
            result = headless.HeadlessRunner.run();

            if (result.getMoves() > 0) {
                jsonHandler.write(result);
            }

        } else {

            Logger.error("Invalid parameter! In case you'd like to run the "
                    + "application in headless mode use the -headless flag.");
        }
    }
}
