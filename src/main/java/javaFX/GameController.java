package javaFX;

import game.Cell;
import game.Game;
import game.Score;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.tinylog.Logger;
import result.Result;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Handles button presses on the game scene.
 */
public class GameController implements Initializable {

    @FXML
    Button backButton;
    @FXML
    GridPane gridPane;
    @FXML
    Label scoreLabel;

    Button[][] buttons = new Button[15][10];

    Cell[][] grid = new Cell[15][10];
    Cell[][] gridSelection = new Cell[15][10];

    Score score = new Score();

    int numberOfTilesCleared = 0;

    int xSelection, ySelection;

    /**
     * When the player presses the back button it switches to the menu scene.
     *
     * @param actionEvent the event in which the button press occurs
     * @throws Exception if any I/O problem occurs
     */
    public void onBackButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Logger.info("Going back to menu...");
        Parent root = FXMLLoader.load(getClass().getResource("/GUIResources/menu.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * When a cell is clicked on this runs the game logic with the selected cell
     * then refreshes the screen according to the changes.
     *
     * @param actionEvent the event in which the button press occurs
     * @throws Exception if any I/O problem occurs
     */
    public void onCellPress(ActionEvent actionEvent) throws Exception {

        xSelection = GridPane.getRowIndex((Node)actionEvent.getSource());
        ySelection = GridPane.getColumnIndex((Node)actionEvent.getSource());

        Game.traverse(grid, gridSelection, xSelection, ySelection,
                grid[xSelection][ySelection].getColor());

        int numberOfSelectedCells =
                Game.getNumberOfNonZeroCells(gridSelection);

        if (numberOfSelectedCells > 1) {
            score.addToScore(Score.calculateScore(numberOfSelectedCells));
            Game.deleteSelectedCells(grid, gridSelection);
            Game.moveDown(grid);
            Game.deleteEmptyColumns(grid);
            numberOfTilesCleared++;
        } else {
            Game.emptyGrid(gridSelection);
        }

        scoreLabel.setText("Score: " + score.getScore());

        setColours();

        if (Game.gameHasEnded(grid)) {

            boolean clearedAllTiles = Game.checkIfAllTilesAreClear(grid);
            String playerName = "";

            Result result = Result.builder()
                    .playerName(playerName)
                    .score(score.getScore())
                    .clearedAllTiles(Boolean.toString(clearedAllTiles))
                    .moves(numberOfTilesCleared)
                    .build();

            Stage stage = (Stage)gridPane.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIResources/endScreen.fxml"));

            Parent root = loader.load();

            EndScreenController endScreenController = loader.getController();
            endScreenController.setResult(result);
            Logger.debug(result);

            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    /**
     * Initializes the game field on startup.
     *
     * @param url the path of the root object
     * @param resourceBundle resources to initialize the root object with
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game.gridInit(grid);
        Game.gridInit(gridSelection);
        initButtons();
        randomizeColours();
    }

    private void setColours() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                if (grid[j][i].getColor() == 1) {
                    buttons[j][i].setStyle("-fx-background-color: Red");
                } else if (grid[j][i].getColor() == 2) {
                    buttons[j][i].setStyle("-fx-background-color: Blue");
                } else if (grid[j][i].getColor() == 3) {
                    buttons[j][i].setStyle("-fx-background-color: Green");
                } else if (grid[j][i].getColor() == 0) {
                    buttons[j][i].setStyle("-fx-background-color: White");
                }
            }
        }
    }

    private void randomizeColours() {
        Random random = new Random();
        int color;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {

                color = random.nextInt(3) + 1;
                if (color == 1) {
                    buttons[j][i].setStyle("-fx-background-color: Red");
                } else if (color == 2) {
                    buttons[j][i].setStyle("-fx-background-color: Blue");
                } else if (color == 3) {
                    buttons[j][i].setStyle("-fx-background-color: Green");
                }
                grid[j][i].setColor(color);
            }
        }
    }

    private void initButtons() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                buttons[j][i] = (Button)gridPane.getChildren().get(i + (j * 10));
            }
        }
    }

}