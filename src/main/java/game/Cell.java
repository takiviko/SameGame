package game;

import lombok.Data;

/**
 * Class for representing a cell from the game field.
 */

@Data
public class Cell {

    public Cell(final int passedColor) {
        this.color = passedColor;
    }

    /**
     * The colour of the cell.
     *     0: empty
     *     1: blue
     *     2: red
     *     3: green
     *     4: yellow
     */
    private int color = 0;

}
