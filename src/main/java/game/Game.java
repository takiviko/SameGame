package game;

import java.util.Random;

public class Game {

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

    public static int getNumberOfTraversedCells(Cell[][] array) {
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

    public static Cell[][] moveLeft(Cell[][] array, int j) {
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

    public static void deleteCells(Cell[][] array, Cell[][] result) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                if(result[i][j].getColor() != 0) {
                    array[i][j].setColor(array[i][j].getColor() - result[i][j].getColor());
                }
            }
        }
        emptyGrid(result);
    }

    public static void emptyGrid(Cell[][] array) {
        for(int i = 0; i<array.length; i++) {
            for(int j = 0; j<array[0].length; j++) {
                array[i][j].setColor(0);
            }
        }
    }

    public static void gridInit(Cell[][] grid, int width, int height) {
        for(int i = 0; i<width; i++) {
            for(int j = 0; j<height; j++) {
                grid[i][j] = new Cell(0);
            }
        }
    }

    public static void randomizeGridValues(int width, int height, Cell[][] grid, int numberOfColors) {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j].setColor(random.nextInt(numberOfColors) + 1);
            }
        }
    }

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
