package game;

import java.util.Scanner;

public class HeadlessRunner {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int width;
        int height;
        int numberOfColors;

        int xSelection;
        int ySelection;

        boolean gameIsRunning = true;

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
        Game.gridInit(grid, width, height);
        Game.gridInit(gridSelection, width, height);
        Game.randomizeGridValues(width, height, grid, numberOfColors);
        System.out.println("Brilliant!\nHere's your grid: ");
        Game.printGridState(grid);

        while(gameIsRunning) {

           xSelection = checkXValue(grid, scanner);
           ySelection = checkYValue(grid, scanner);


            Game.traverse(grid, gridSelection, ySelection, xSelection, grid[ySelection][xSelection].getColor());
            int supposedScore = Game.getNumberOfTraversedCells(gridSelection);
            if(supposedScore > 1) {
                score.addToScore((int)Math.pow((Game.getNumberOfTraversedCells(gridSelection)-2), 2));
                Game.deleteCells(grid, gridSelection);
                Game.moveDown(grid);
                Game.deleteEmptyColumns(grid);
                System.out.println("Current score: " + score.getScore());
                System.out.println("Here's your grid after the turn: ");
            } else {
                Game.deleteCells(grid, gridSelection);
                System.out.println("Invalid selection!");
            }
            Game.printGridState(grid);
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
