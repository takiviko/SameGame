package game;

import lombok.Data;

/**
 * Class for representing a cell from the game field.
 */

@Data
public class Cell {

    /**
     * Sets the default value of the cell's colour to the passed number.
     *
     * @param passedColor the number to set the colour of the cell to
     */
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
