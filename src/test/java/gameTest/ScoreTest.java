package gameTest;

import game.Score;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    @Test
    public void constructorTest() {
        Score score0 = new Score();
        assertEquals(0, score0.getScore());

        Score score5 = new Score(5);
        assertEquals(5, score5.getScore());
    }

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

    @Test
    public void resetScoreTest() {
        Score score = new Score(5);
        score.resetScore();
        assertEquals(0, score.getScore());

        Score score1 = new Score();
        score1.resetScore();
        assertEquals(0, score1.getScore());

    }
}
