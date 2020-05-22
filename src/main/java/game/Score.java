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

    public void resetScore() {
        score = 0;
    }
}
