package javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;
import result.JsonHandler;
import result.Result;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles button presses on the end scene.
 */
public class EndScreenController {

    @FXML
    Button okButton;
    @FXML
    Label resultLabel;
    @FXML
    TextField textField;

    Result result = new Result();

    /**
     * Gets a {@code Result} objects and initializes the local {@code Result} variable with it.
     *
     * @param result the passed {@code Result} object
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * When the player presses the "Get Result" button
     * it writes the results to the screen in a formatted manner.
     *
     * @param actionEvent the event in which the button press occurs
     */
    public void onResultButtonClick(ActionEvent actionEvent) {
        setResult(result);
        resultLabel.setText(result.toFormattedStringWithoutName());
    }

    /**
     * When the player presses the OK button
     * the method writes the results into the scores.json file
     * and switches the scene back to the menu.
     *
     * @param actionEvent the event in which the button press occurs
     * @throws IOException if and I/O problem occurs
     */
    public void onOKButtonClick(ActionEvent actionEvent) throws IOException {
        Logger.debug(result);
        String name = textField.getCharacters().toString();
        result.setPlayerName(name);

        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.write(result);
        Logger.info("Results saved in scores.json");

        Stage stage = (Stage)okButton.getScene().getWindow();
        Logger.info("Switching to menu scene");
        Parent root = FXMLLoader.load(getClass().getResource("/GUIResources/menu.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
