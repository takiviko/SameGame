package javaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.tinylog.Logger;
import javafx.event.ActionEvent;
import result.JsonHandler;
import result.Result;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {

    @FXML
    Button backButton;

    @FXML
    TableView<Result> tableView;

    @FXML
    TableColumn<Result, String> nameColumn;

    @FXML
    TableColumn<Result, Integer> scoreColumn;

    @FXML
    TableColumn<Result, Integer> movesColumn;

    @FXML
    TableColumn<Result, String> clearedAllTilesColumn;

    @FXML
    Button deleteButton;

    public void onBackButtonClicked(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)backButton.getScene().getWindow();
        Logger.info("Switching to menu scene");
        Parent root = FXMLLoader.load(getClass().getResource("/GUIResources/menu.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Result[] results = JsonHandler.readGsonArray();
            setTableItems(results);
        } catch (Exception e) {
            tableView.getItems().clear();
            Logger.debug("No Items!");
        }
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        JsonHandler jsonHandler = new JsonHandler();
        jsonHandler.delete();
        deleteTableItems();
    }

    private void setTableItems(Result[] results) {
        ObservableList<Result> resultList = FXCollections.observableArrayList();
        Collections.addAll(resultList, results);
        for (int i = 0; i < results.length; i++) {
            nameColumn.setCellValueFactory(
                    new PropertyValueFactory("playerName")
            );
            scoreColumn.setCellValueFactory(
                    new PropertyValueFactory("score")
            );
            movesColumn.setCellValueFactory(
                    new PropertyValueFactory("moves")
            );
            clearedAllTilesColumn.setCellValueFactory(
                    new PropertyValueFactory("clearedAllTiles")
            );

            tableView.setItems(resultList);
        }
    }

    private void deleteTableItems() {
        for ( int i = 0; i<tableView.getItems().size(); i++) {
            tableView.getItems().clear();
        }
    }
}
