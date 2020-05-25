package headless;

import game.Cell;
import game.Game;
import game.Score;
import result.Result;

import java.util.Scanner;

/**
 * A class containing methods for running the game in headless mode.
 */
public final class HeadlessRunner {

    private HeadlessRunner() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Runs the game in headless mode.
     *
     * @return a Result object representing the game score
     */
    public static Result run() {

        String playerName;
        boolean clearedAllTiles;
        int numberOfTilesCleared = 0;

        Scanner scanner = new Scanner(System.in);
        int height = 15;
        int width = 10;
        int numberOfColors = 3;

        int xSelection;
        int ySelection;

        Score score = new Score();

        //---------------------------------------------------------//

        Cell[][] grid = new Cell[height][width];
        Cell[][] gridSelection = new Cell[height][width];
        Game.gridInit(grid);
        Game.gridInit(gridSelection);
        Game.randomizeGridValues(grid, numberOfColors);
        System.out.println("Brilliant!\nHere's your grid: ");
        Game.printGridState(grid);

        while (true) {

            if (Game.gameHasEnded(grid)) {

                clearedAllTiles = Game.checkIfAllTilesAreClear(grid);

                System.out.println("Your Score: " + score.getScore());

                System.out.println("Enter your name: ");
                Scanner stringScanner = new Scanner(System.in);
                playerName = stringScanner.nextLine();
                return Result.builder()
                        .playerName(playerName)
                        .score(score.getScore())
                        .clearedAllTiles(clearedAllTiles)
                        .moves(numberOfTilesCleared)
                        .build();
            }

            xSelection = checkXValue(grid, scanner);
            ySelection = checkYValue(grid, scanner);

            Game.traverse(grid, gridSelection, ySelection, xSelection,
                    grid[ySelection][xSelection].getColor());

            int numberOfSelectedCells =
                    Game.getNumberOfNonZeroCells(gridSelection);

            if (numberOfSelectedCells > 1) {
                score.addToScore(Score.calculateScore(numberOfSelectedCells));
                Game.deleteSelectedCells(grid, gridSelection);
                Game.moveDown(grid);
                Game.deleteEmptyColumns(grid);
                System.out.println("Current score: " + score.getScore());
                System.out.println("Here's your grid after the turn: ");
                numberOfTilesCleared++;
            } else {
                Game.emptyGrid(gridSelection);
                System.out.println("Invalid selection!");
            }
            Game.printGridState(grid);
        }
    }

    private static int checkXValue(final Cell[][] grid, final Scanner scanner) {
        while (true) {
            System.out.println("Enter the x position of your selection: ");
            int xSelection = scanner.nextInt() - 1;
            if (xSelection >= 0 && xSelection < grid[0].length) {
                return xSelection;
            } else {
                System.out.println("Incorrect value: " + (xSelection + 1));
            }
        }
    }

    private static int checkYValue(final Cell[][] grid, final Scanner scanner) {
        while (true) {
            System.out.println("Enter the y position of your selection: ");
            int ySelection = scanner.nextInt() - 1;
            if (ySelection >= 0 && ySelection < grid.length) {
                return ySelection;
            } else {
                System.out.println("Incorrect value: " + (ySelection + 1));
            }
        }
    }

}
