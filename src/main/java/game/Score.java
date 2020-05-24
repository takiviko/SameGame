package game;

import lombok.Getter;

@Getter
public class Score {

    /**
     * The score of the player at a given time.
     */
    int score;

    /**
     * Sets the initial score to zero.
     */
    public Score() {
        this.score = 0;
    }

    /**
     * Adds the passed number to the score.
     *
     * @param n the number to be added to the score
     */
    public void addToScore(int n) {
        score = score + n;
    }

    /**
     * Calculates the score from the given number using the formula {@code (n-2)^2}.
     *
     * @param n the number to calculate the score from
     * @return the calculated score
     */
    public static int calculateScore(int n) {
        return (int)Math.pow((n-2), 2);
    }

    /**
     * Resets the score to zero.
     */
    public void resetScore() {
        score = 0;
    }
}
