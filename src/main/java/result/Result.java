package result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
    private int numberOfTilesCleared;

    /**
     * Indicates whether the player has cleared all the tiles or not.
     */
    private boolean clearedAllTiles;
}
