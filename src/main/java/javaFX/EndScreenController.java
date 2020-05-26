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

public class EndScreenController implements Initializable {

    @FXML
    Button okButton;
    @FXML
    Label resultLabel;
    @FXML
    TextField textField;

    Result result = new Result();

    public void setResult(Result result) {
        this.result = result;
        Logger.debug(result);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onResultButtonClick(ActionEvent actionEvent) {
        setResult(result);

        resultLabel.setText(result.toFormattedStringWithoutName());

    }

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
