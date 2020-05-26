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

public class MenuController {
    @FXML
    Button startButton, highScoreButton, exitButton;


    @FXML
    public void onExitButtonClicked(ActionEvent actionEvent) {
        Logger.info("Exiting application...");
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void onHighScoreButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)highScoreButton.getScene().getWindow();
        Logger.info("Switching to high scores scene");
        Parent root = FXMLLoader.load(this.getClass().getResource("/GUIResources/highScores.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onStartButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)startButton.getScene().getWindow();
        Logger.info("Starting game...");
        Parent root = FXMLLoader.load(getClass().getResource("/GUIResources/game.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
