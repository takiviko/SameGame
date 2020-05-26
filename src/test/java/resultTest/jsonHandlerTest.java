package resultTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import result.JsonHandler;
import result.Result;

import java.io.IOException;
import java.util.ArrayList;

public class jsonHandlerTest {

    @Test
    public void readTest() {
        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();
        initializeTest(jsonHandler, result);

        try {
            assertEquals(result.getPlayerName(), jsonHandler.readGsonArray()[0].getPlayerName());
            assertEquals(result.getScore(), jsonHandler.readGsonArray()[0].getScore());
            assertEquals(result.getMoves(), jsonHandler.readGsonArray()[0].getMoves());
            assertEquals(result.getClearedAllTiles(), jsonHandler.readGsonArray()[0].getClearedAllTiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTest() {
        JsonHandler jsonHandler = new JsonHandler();
        Result result = new Result();
        initializeTest(jsonHandler, result);

        try {
            assertNotEquals(null, jsonHandler.readGsonArray()[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonHandler.delete();

        try {
            assertEquals(NullPointerException.class, jsonHandler.readGsonArray()[0].getPlayerName());
        } catch (Exception ignored) {}
    }

    private void initializeTest(JsonHandler jsonHandler, Result result) {
        result.setPlayerName("MyName");
        result.setScore(30);
        result.setMoves(5);
        result.setClearedAllTiles(Boolean.toString(false));

        jsonHandler.delete();
        jsonHandler.write(result);
    }

}
