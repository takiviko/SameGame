package resultTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import result.Result;

public class ResultTest {

    @Test
    public void toFormattedStringWithoutNameTest() {
        Result result = Result.builder()
                .playerName("DoNotWriteItOut")
                .score(30)
                .moves(5)
                .clearedAllTiles(Boolean.toString(false))
                .build();

        Result result1 = Result.builder()
                .playerName("Different!")
                .score(31)
                .moves(6)
                .clearedAllTiles(Boolean.toString(true))
                .build();

        Result result2 = Result.builder()
                .playerName("DoNotWriteItOut")
                .score(30)
                .moves(6)
                .clearedAllTiles(Boolean.toString(false))
                .build();

        assertEquals(
                "Score: 30\nMoves: 5\nCleared all tiles: false"
                , result.toFormattedStringWithoutName()
        );

        assertEquals(-1, result.compare(result, result1));
        assertEquals(0, result.compare(result, result2));
        assertNotEquals(-1,result.compare(result, result2));
    }

    @Test
    public void compareTest() {
        Result result = Result.builder()
                .playerName("DoNotWriteItOut")
                .score(30)
                .moves(5)
                .clearedAllTiles(Boolean.toString(false))
                .build();

        Result result1 = Result.builder()
                .playerName("Different!")
                .score(31)
                .moves(6)
                .clearedAllTiles(Boolean.toString(true))
                .build();

        Result result2 = Result.builder()
                .playerName("DoNotWriteItOut")
                .score(30)
                .moves(6)
                .clearedAllTiles(Boolean.toString(false))
                .build();

        assertEquals(-1, result.compare(result, result1));
        assertEquals(0, result.compare(result, result2));
        assertNotEquals(-1,result.compare(result, result2));
    }

}
