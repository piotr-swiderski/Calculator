package sample.pl.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

public class AppScreenController {

    private MainController mainController;

    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField fromX;
    @FXML
    private TextField toX;
    @FXML
    private TextField stepX;
    @FXML
    private TextArea chartFunction;

    public void buttonBack() {
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


    public void buttonCalculate() {
        calculator();
    }

    public void calculator() {
        if (!textArea.getText().isEmpty()) {
            ScriptEngine scriptEngine;
            scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
            try {
                textField.setText(scriptEngine.eval(textArea.getText()).toString());
            } catch (ScriptException e) {
                e.printStackTrace();
                textField.setText("Wrong operation!!!");
            }
        }
    }

    public void areaKeyPressed(KeyEvent event){
       if(event.getCode().equals(KeyCode.ENTER)){
           calculator();
       }
    }

    public void buttonChart(){
        /*Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/ChartScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ChartController chartController = ChartController.getInstance();
        chartController.getDataToChart(
                Double.parseDouble(fromX.getText()),
                Double.parseDouble(toX.getText()),
                Double.parseDouble(stepX.getText()),
                chartFunction.getText()
        );
        try {
            chartController.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*stage.setTitle("Chart");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();*/


    }




}
