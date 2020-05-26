package javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

public class SameGameApplication  extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Logger.info("Starting application...");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/GUIResources/menu.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("SameGame");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
