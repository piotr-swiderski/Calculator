package sample.pl.app.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scope extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/ChartScreen.fxml"));
        primaryStage.setTitle("Char");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
