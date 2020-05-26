package gameTest;

import game.Cell;
import game.Game;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Cell[][] grid = new Cell[3][3];

    private void emptyGridInit(Cell[][] grid) {
        Game.gridInit(grid);
        Game.emptyGrid(grid);
    }

    private void randomGridInit(Cell[][] grid) {
        Game.gridInit(grid);
        Game.randomizeGridValues(grid, 3);
    }

    private void standardGridInit(Cell[][] grid) {
        Game.gridInit(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setColor(1);
            }
        }
    }

    private void gridWithEmptyRowInit(Cell[][] grid) {
        Game.gridInit(grid);
        for (int i = 0; i < grid[0].length; i++) {
            grid[0][i].setColor(1);
        }
        Game.printGridState(grid);
    }

    @Test
    public void emptyGridTest() {
        emptyGridInit(grid);
        assertEquals(0, grid[0][0].getColor());
    }

    @Test
    public void redGridTest() {
        for(int i = 0; i < 100; i++) {
            randomGridInit(grid);
            Logger.debug(grid[0][0].getColor());
            assertTrue(grid[0][0].getColor() == 1 || grid[0][0].getColor() == 2 || grid[0][0].getColor() == 3);
        }
    }

    @Test
    public void checkIfAllTilesAreClearTest() {
        randomGridInit(grid);
        assertFalse(Game.checkIfAllTilesAreClear(grid));

        emptyGridInit(grid);
        assertTrue(Game.checkIfAllTilesAreClear(grid));
    }

    @Test
    public void gameHasEndedTest() {
        standardGridInit(grid);
        assertFalse(Game.gameHasEnded(grid));

        emptyGridInit(grid);
        assertTrue(Game.gameHasEnded(grid));
    }

    @Test
    public void getNumberOfNonZeroCellsTest() {
        standardGridInit(grid);
        assertEquals(9, Game.getNumberOfNonZeroCells(grid));

        emptyGridInit(grid);
        assertEquals(0, Game.getNumberOfNonZeroCells(grid));
    }

    @Test
    public void moveLeftTest() {
        gridWithEmptyRowInit(grid);
        Game.moveDown(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i != grid.length - 1) {
                    assertEquals(0, grid[i][j].getColor());
                } else {
                    assertEquals(1, grid[i][j].getColor());
                }
            }
        }
    }


}

/*
for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {

            }
        }
 */