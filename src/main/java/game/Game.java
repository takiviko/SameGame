package game;

import java.util.Random;
import org.tinylog.Logger;

/**
 * Class holding the necessary methods for the game.
 */

public final class Game {

    private Game() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if there are possible moves to perform at a given state.
     *
     * @param array {@code Cell[][]} matrix representing the state of the game
     * @return {@code true} if there are no possible moves,
     *         {@code false} otherwise
     */
    public static boolean gameHasEnded(final Cell[][] array) {
        Cell[][] tempArray = new Cell[array.length][array[0].length];
        gridInit(tempArray);

        boolean gameHasEnded = true;

        if (getNumberOfNonZeroCells(array) == 0) {
            Logger.info("There are no more coloured cells.");
            return true;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].getColor() != 0) {
                    traverse(array, tempArray, i, j, array[i][j].getColor());
                    if (getNumberOfNonZeroCells(tempArray) > 1) {
                        gameHasEnded = false;
                    }
                    emptyGrid(tempArray);
                }
            }
        }

        if (gameHasEnded) {
            Logger.info("There are no more possible moves.");
        }

        return gameHasEnded;
    }

    /**
     * Method for finding all cells with the same colour starting from a
     * given position if one can only move the four cardinal directions.
     *
     * @param array {@code Cell[][]} array representing the game state
     * @param selection {@code Cell[][]} array representing the current
     *                      selection
     * @param selectedHeight the y position of the selected starting point
     * @param selectedWidth the x position of the selected starting point
     * @param color the colour of the starting cell,
     *              ideally received with a getter
     */
    public static void traverse(final Cell[][] array, final Cell[][] selection,
           final int selectedHeight, final int selectedWidth, final int color) {

        if (array[selectedHeight][selectedWidth].getColor() == 0) {
            System.out.println("The selected cell is empty");
            return;
        }

        selection[selectedHeight][selectedWidth]
                .setColor(array[selectedHeight][selectedWidth].getColor());

        //Check south
        if (selectedHeight + 1 < array.length) {
            if (array[selectedHeight + 1][selectedWidth].getColor() == color
              && selection[selectedHeight + 1][selectedWidth].getColor() == 0) {
                traverse(array, selection,
                        selectedHeight + 1, selectedWidth, color);
            }
        }

        //Check north
        if (selectedHeight > 0) {
            if (array[selectedHeight - 1][selectedWidth].getColor() == color
              && selection[selectedHeight - 1][selectedWidth].getColor() == 0) {
                traverse(array, selection,
                        selectedHeight - 1, selectedWidth, color);
            }
        }

        //Check east
        if (selectedWidth + 1 < array[0].length) {
            if (array[selectedHeight][selectedWidth + 1].getColor() == color
              && selection[selectedHeight][selectedWidth + 1].getColor() == 0) {
                traverse(array, selection,
                        selectedHeight, selectedWidth + 1, color);
            }
        }

        //Check west
        if (selectedWidth > 0) {
            if (array[selectedHeight][selectedWidth - 1].getColor() == color
              && selection[selectedHeight][selectedWidth - 1].getColor() == 0) {
                traverse(array, selection,
                        selectedHeight, selectedWidth - 1, color);
            }
        }
    }

    /**
     * Moves all non-zero cells down that have one or more zeros below them
     * until there are no more zeros below them.
     *
     * @param array a given state of the game
     */
    public static void moveDown(final Cell[][] array) {
        int zerosBelow;
        for (int i = array.length - 2; i >= 0; i--) {
            for (int j = array[0].length - 1; j >= 0; j--) {
                zerosBelow = 0;
                if (array[i][j].getColor() != 0) {
                    for (int x = i; x < array.length - 1
                            && array[x + 1][j].getColor() == 0; x++) {
                        zerosBelow++;
                    }
                    if (zerosBelow > 0) {
                        array[i + zerosBelow][j]
                                .setColor(array[i][j].getColor());
                        array[i][j].setColor(0);
                    }
                }
            }
        }
    }

    /**
     * Returns the number of non-zero cells in a given array.
     *
     * @param array the array in which the function finds the non-zero cells
     * @return the number of non-zero cells in the array
     */
    public static int getNumberOfNonZeroCells(final Cell[][] array) {
        int score = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].getColor() != 0) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * Moves cells to the left if there is at least one empty
     * (full of zeros) column to their left.
     *
     * @param array the array to perform the action on
     * @return the new game state with all the empty columns on the right side
     */
    public static Cell[][] deleteEmptyColumns(final Cell[][] array) {
        boolean columnIsEmpty;
        for (int k = 0; k < array[0].length; k++) {
            for (int j = 0; j < array[0].length; j++) {
                columnIsEmpty = true;
                for (int i = 0; i < array.length; i++) {
                    if (array[i][j].getColor() != 0) {
                        columnIsEmpty = false;
                    }
                }
                if (columnIsEmpty) {
                    moveLeft(array, j);
                }
            }
        }
        return array;
    }

    private static Cell[][] moveLeft(final Cell[][] array, final int column) {
        int j = column;
        for (; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (j < array[0].length - 1) {
                    array[i][j].setColor(array[i][j + 1].getColor());
                } else {
                    array[i][j].setColor(0);
                }
            }
        }
        return array;
    }

    /**
     * Sets the values of the cells to zero on the game field
     * if they match the selection.
     *
     * @param array the game field
     * @param selection the selection
     */
    public static void deleteSelectedCells(
            final Cell[][] array, final Cell[][] selection) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (selection[i][j].getColor() != 0) {
                    array[i][j].setColor(array[i][j].getColor()
                            - selection[i][j].getColor());
                }
            }
        }
        emptyGrid(selection);
    }

    /**
     * Sets all values of a game field to zero.
     *
     * @param array the game field to empty
     */
    public static void emptyGrid(final Cell[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j].setColor(0);
            }
        }
    }

    /**
     * Initializes a {@code Cell[][]} array and sets the cell values to zero.
     *
     * @param grid the {@code Cell[][]} array to be initialized
     */
    public static void gridInit(final Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Cell(0);
            }
        }
    }

    /**
     * Randomizes all the values in a given {@code Cell[][]} array.
     *
     * @param grid the array to be randomized
     * @param numberOfColors the number of possible colours
     *          in the randomized array
     */
    public static void randomizeGridValues(final Cell[][] grid,
                                           final int numberOfColors) {
        Random random = new Random();
        Logger.info("Shuffling grid colours");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setColor(random.nextInt(numberOfColors) + 1);
            }
        }
    }

    /**
     * Prints the values of the {@code Cell[][]} array to the console.
     *
     * @param grid the array to be printed
     */
    public static void printGridState(final Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Returns {@code true} if all tiles are empty in the passed {@code Cell[][]} object,
     * {@code false otherwise}.
     *
     * @param grid The {@code Cell[][]} object to be checked
     * @return {@code true} if all tiles are empty in the passed {@code Cell[][]} object,
     *      {@code false} otherwise
     */
    public static boolean checkIfAllTilesAreClear(final Cell[][] grid) {
        if (getNumberOfNonZeroCells(grid) > 0) {
            return false;
        }
        return true;
    }
}
