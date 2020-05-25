package javaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.tinylog.Logger;
import javafx.event.ActionEvent;

public class HighScoresController {

    @FXML
    Button backButton;

    public void onBackButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Logger.info("Switching to high scores scene");
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
