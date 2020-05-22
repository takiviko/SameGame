import static org.junit.jupiter.api.Assertions.*;

import game.Score;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    public void testScore() {
        assertEquals(0, Score.calculateScore(2));
        assertEquals(4, 4);
        assertEquals(81, Score.calculateScore(11));
    }
}
