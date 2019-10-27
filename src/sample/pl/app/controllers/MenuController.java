package sample.pl.app.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private MainController mainController;

    @FXML
    public void buttonApp() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../resources/fxml/AppScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
        AppScreenController appScreenController = loader.getController();
        appScreenController.setMainController(mainController);

    }

    @FXML
    public void buttonOptions() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../resources/fxml/OptionsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
        OptionsController optionsController = loader.getController();
        optionsController.setMainController(mainController);
    }


    @FXML
    public void buttonExit() {
        Platform.exit();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
