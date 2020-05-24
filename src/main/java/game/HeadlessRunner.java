package game;

import result.Result;

import java.util.Scanner;

public class HeadlessRunner {
    public static Result run() {

        String playerName;
        boolean clearedAllTiles;
        int numberOfTilesCleared = 0;

        Scanner scanner = new Scanner(System.in);
        int width;
        int height;
        int numberOfColors;

        int xSelection;
        int ySelection;

        Score score = new Score();

        //--------------------------------------------------------------------//

        System.out.println("Welcome!\nEnter the width of your grid: ");
        width = scanner.nextInt();
        System.out.println("Enter the height of your grid: ");
        height = scanner.nextInt();


        Cell[][] grid = new Cell[width][height];
        Cell[][] gridSelection = new Cell[width][height];

        System.out.println("Now enter the number of colours you want in your grid: ");
        numberOfColors = scanner.nextInt();
        Game.gridInit(grid);
        Game.gridInit(gridSelection);
        Game.randomizeGridValues(grid, numberOfColors);
        System.out.println("Brilliant!\nHere's your grid: ");
        Game.printGridState(grid);

        while(true) {

            if(Game.gameHasEnded(grid)) {

                clearedAllTiles = checkIfAllTilesAreClear(grid);

                System.out.println("Your Score: " + score.getScore());

                System.out.println("Enter your name: ");
                Scanner stringScanner = new Scanner(System.in);
                playerName = stringScanner.nextLine();
                return Result.builder()
                        .playerName(playerName)
                        .score(score.getScore())
                        .clearedAllTiles(clearedAllTiles)
                        .numberOfTilesCleared(numberOfTilesCleared)
                        .build();
            }

           xSelection = checkXValue(grid, scanner);
           ySelection = checkYValue(grid, scanner);

            Game.traverse(grid, gridSelection, ySelection, xSelection, grid[ySelection][xSelection].getColor());
            int numberOfSelectedCells = Game.getNumberOfNonZeroCells(gridSelection);
            if(numberOfSelectedCells > 1) {
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

    private static boolean checkIfAllTilesAreClear(Cell[][] grid) {
        if(Game.getNumberOfNonZeroCells(grid) > 0) {
            return false;
        } else {
            return true;
        }
    }

    private static int checkXValue(Cell[][] grid, Scanner scanner) {
        while(true) {
            System.out.println("Enter the x position of your selection: ");
            int xSelection = scanner.nextInt() - 1;
            if(xSelection >= 0 && xSelection < grid[0].length) {
                return xSelection;
            } else {
                System.out.println("Incorrect value: " + (xSelection + 1));
            }
        }
    }

    private static int checkYValue(Cell[][] grid, Scanner scanner) {
        while(true) {
            System.out.println("Enter the y position of your selection: ");
            int ySelection = scanner.nextInt() - 1;
            if(ySelection >= 0 && ySelection < grid.length) {
                return ySelection;
            } else {
                System.out.println("Incorrect value: " + (ySelection + 1));
            }
        }
    }

}
