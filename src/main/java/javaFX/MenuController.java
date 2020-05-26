package javaFX;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.tinylog.Logger;

/**
 * The controller class of the initial scene of the application.
 */
public class MenuController {
    @FXML
    Button startButton, highScoreButton, exitButton;


    /**
     * When the exit button is clicked the method shuts down the application.
     *
     * @param actionEvent the event in which the button press occurs
     */
    @FXML
    public void onExitButtonClicked(ActionEvent actionEvent) {
        Logger.info("Exiting application...");
        Platform.exit();
        System.exit(0);
    }

    /**
     * When the high scores button is clicked the scene gets switched to the high scores scene.
     *
     * @param actionEvent the event in which the button press occurs
     * @throws Exception if any I/O problem occurs
     */
    @FXML
    public void onHighScoreButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)highScoreButton.getScene().getWindow();
        Logger.info("Switching to high scores scene");
        Parent root = FXMLLoader.load(this.getClass().getResource("/GUIResources/highScores.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * When the start button is clicked the game scene loads and starts.
     *
     * @param actionEvent the event in which the button press occurs
     * @throws Exception if any I/O problem occurs
     */
    @FXML
    public void onStartButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)startButton.getScene().getWindow();
        Logger.info("Starting game...");
        Parent root = FXMLLoader.load(getClass().getResource("/GUIResources/game.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
