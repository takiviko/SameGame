package game;

import lombok.Getter;

@Getter
public class Score {

    int score;

    public Score() {
        this.score = 0;
    }

    public void addToScore(int n) {
        score = score + n;
    }

    public static int calculateScore(int n) {
        return (int)Math.pow((n-2), 2);
    }

    public void resetScore() {
        score = 0;
    }
}
