package result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for representing the results of a given round of the game.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

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
    private boolean clearedAllTiles;
}
