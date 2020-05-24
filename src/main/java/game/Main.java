package game;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        run(args[0]);
    }

    private static void run(String mode) {
        if(mode.equals("")) {
            //Run the gui
        } else if(mode.equals("-headless")) {
                HeadlessRunner.run();
        } else {
            throw new InvalidParameterException();
        }
    }
}