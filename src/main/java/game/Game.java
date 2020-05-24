package game;

import java.util.Random;

public class Game {

    /**
     * Checks if there are possible moves to perform at a given state.
     *
     * @param array a state of the game represented by a {@code Cell[][]} two-dimensional matrix
     * @return {@code true} if there are no possible moves left, {@code false} otherwise
     */
    public static boolean gameHasEnded(Cell[][] array) {
        Cell[][] tempArray = new Cell[array.length][array[0].length];
        gridInit(tempArray);

        boolean gameHasEnded = true;

        if(getNumberOfNonZeroCells(array) == 0) {
            return true;
        }

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length;j++) {
                if(array[i][j].getColor() != 0) {
                    traverse(array, tempArray, i, j, array[i][j].getColor());
                    if(getNumberOfNonZeroCells(tempArray) > 1) {
                        gameHasEnded = false;
                    }
                    emptyGrid(tempArray);
                }
            }
        }
        return gameHasEnded;
    }

    public static void traverse(Cell[][] array, Cell[][] selection, int selectedHeight, int selectedWidth, final int color) {

        if(array[selectedHeight][selectedWidth].getColor() == 0) {
            System.out.println("The selected cell is empty");
            return;
        }

        selection[selectedHeight][selectedWidth].setColor(array[selectedHeight][selectedWidth].getColor());

        //Check south
        if(selectedHeight + 1 < array.length) {
            if(array[selectedHeight + 1][selectedWidth].getColor() == color && selection[selectedHeight + 1][selectedWidth].getColor() == 0) {
                traverse(array, selection, selectedHeight + 1, selectedWidth, color);
            }
        }

        //Check north
        if(selectedHeight > 0) {
            if(array[selectedHeight - 1][selectedWidth].getColor() == color && selection[selectedHeight - 1][selectedWidth].getColor() == 0) {
                traverse(array, selection, selectedHeight - 1, selectedWidth, color);
            }
        }

        //Check east
        if(selectedWidth + 1 < array[0].length) {
            if(array[selectedHeight][selectedWidth + 1].getColor() == color && selection[selectedHeight][selectedWidth + 1].getColor() == 0) {
                traverse(array, selection, selectedHeight, selectedWidth + 1, color);
            }
        }

        //Check west
        if(selectedWidth > 0) {
            if(array[selectedHeight][selectedWidth - 1].getColor() == color && selection[selectedHeight][selectedWidth - 1].getColor() == 0) {
                traverse(array, selection, selectedHeight, selectedWidth - 1, color);
            }
        }
    }

    /**
     * Moves all non-zero cells down that have one or more zeros below them until there are no more zeros below them.
     *
     * @param array a given state of the game
     */
    public static void moveDown(Cell[][] array) {
        int zerosBelow;
        for(int i = array.length - 2; i >= 0; i--) {
            for(int j = array[0].length - 1; j >= 0; j--) {
                zerosBelow = 0;
                if(array[i][j].getColor() != 0) {
                    for(int x = i; x < array.length - 1 && array[x+1][j].getColor() == 0; x++) {
                        zerosBelow++;
                    }
                    if(zerosBelow > 0) {
                        array[i+zerosBelow][j].setColor(array[i][j].getColor());
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
    public static int getNumberOfNonZeroCells(Cell[][] array) {
        int score = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j].getColor() != 0) {
                    score++;
                }
            }
        }
        return score;
    }

    /**
     * Moves cells to the left if there is at least one empty (full of zeros) column to their left.
     *
     * @param array the array to perform the action on
     * @return the new game state with all the empty columns on the right side
     */
    public static Cell[][] deleteEmptyColumns(Cell[][] array) {
        boolean columnIsEmpty;
        for(int k = 0; k < array[0].length; k++) {
            for(int j = 0; j < array[0].length; j++) {
                columnIsEmpty = true;
                for(int i = 0; i<array.length; i++) {
                    if(array[i][j].getColor() != 0) {
                        columnIsEmpty = false;
                    }
                }
                if(columnIsEmpty) {
                    moveLeft(array, j);
                }
            }
        }
        return array;
    }

    private static Cell[][] moveLeft(Cell[][] array, int j) {
        for(; j < array[0].length; j++) {
            for(int i = 0; i < array.length; i++) {
                if(j < array.length-1) {
                    array[i][j].setColor(array[i][j+1].getColor());
                } else {
                    array[i][j].setColor(0);
                }
            }
        }
        return array;
    }

    /**
     * Sets the values of the cells to zero on the game field if they match the selection.
     *
     * @param array the game field
     * @param selection the selection
     */
    public static void deleteSelectedCells(Cell[][] array, Cell[][] selection) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                if(selection[i][j].getColor() != 0) {
                    array[i][j].setColor(array[i][j].getColor() - selection[i][j].getColor());
                }
            }
        }
        emptyGrid(selection);
    }

    /**
     * Sets all values of a game field to zero
     *
     * @param array the game field to empty
     */
    public static void emptyGrid(Cell[][] array) {
        for(int i = 0; i<array.length; i++) {
            for(int j = 0; j<array[0].length; j++) {
                array[i][j].setColor(0);
            }
        }
    }

    /**
     * Initializes a {@code Cell[][]} array and sets the cell values to zero.
     *
     * @param grid the {@code Cell[][]} array to be initialized
     */
    public static void gridInit(Cell[][] grid) {
        for(int i = 0; i<grid.length; i++) {
            for(int j = 0; j<grid[0].length; j++) {
                grid[i][j] = new Cell(0);
            }
        }
    }

    /**
     * Randomizes all the values in a given {@code Cell[][]} array
     *
     * @param grid the array to be randomized
     * @param numberOfColors the number of possible colours in the randomized array
     */
    public static void randomizeGridValues(Cell[][] grid, int numberOfColors) {
        Random random = new Random();
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
    public static void printGridState(Cell[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j].getColor() + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
