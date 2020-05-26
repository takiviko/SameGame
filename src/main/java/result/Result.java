package result;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * Class for representing the results of a given round of the game.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Comparator<Result> {

    /**
     * The name of the player.
     */
    private String playerName;

    /**
     * The score of the player.
     */
    private int score;

    /**
     * The number of tiles cleared by the player.
     */
    private int moves;

    /**
     * Indicates whether the player has cleared all the tiles or not.
     */
    private String clearedAllTiles;

    public String toFormattedString() {
        return "Name: " + playerName + "\nScore: " + score + "\nMoves: " + moves + "\nCleared all tiles: " + clearedAllTiles;
    }

    public String toFormattedStringWithoutName() {
        return "Score: " + score + "\nMoves: " + moves + "\nCleared all tiles: " + clearedAllTiles;
    }



    @Override
    public int compare(Result o1, Result o2) {
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}
