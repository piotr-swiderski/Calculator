package sample.pl.app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class OptionsController {

    private MainController mainController;

    public void buttonBack(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../resources/fxml/MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainController.setScreen(pane);
        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);

    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
