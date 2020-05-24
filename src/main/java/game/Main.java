package game;

import result.JsonHandler;
import result.Result;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        run(args[0]);
    }

    private static void run(String mode) {

        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();

        if(mode.equals("")) {
            //TODO Run the gui
        } else if(mode.equals("-headless")) {
                result = HeadlessRunner.run();
        } else {
            throw new InvalidParameterException();
        }
        jsonHandler.write(result);
    }
}