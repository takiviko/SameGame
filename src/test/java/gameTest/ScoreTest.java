package gameTest;

import game.Score;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {
    @Test
    public void testScore() {
        assertEquals(0, Score.calculateScore(2));
        assertEquals(4, 4);
        assertEquals(81, Score.calculateScore(11));
    }

    @Test
    public void testAddToScore() {
        Score score = new Score();
        score.addToScore(0);
        assertEquals(0, score.getScore());

        score.addToScore(2);
        assertEquals(2, score.getScore());

        score.addToScore(4);
        assertEquals(6, score.getScore());
    }
}
