package sample.pl.app.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChartController extends Application {

    private static double fromX;
    private static double toX;
    private static double stepX;
    private static String function;
    private FileWriter fw;
    private Parent root = null;


    @FXML
    LineChart<Number, Number> lineChart;
    @FXML
    TextField pathText;
    @FXML
    Pane pane;

    private static ChartController instance = new ChartController();

    public static ChartController getInstance() {
        return instance;
    }


    public ChartController() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //lineChart.prefHeightProperty().bind(pane.heightProperty());
        pane = new StackPane();
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/fxml/ChartScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Chart");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();
    }

    public void fileChooser() {
    }


    public void buttonDraw() {
        System.out.println("Draw!");
        lineChart.setTitle("Wygenerowany wykres");
        lineChart.getData().clear();
        System.out.println(pathText);
        try {
            fw = new FileWriter(pathText.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        XYChart.Series series = makeSeries();
        series.setName("f(x)=" + function.toString());
        lineChart.getData().add(series);
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void getDataToChart(double fromX, double toX, double stepX, String function) {
        this.fromX = fromX;
        this.toX = toX;
        this.stepX = stepX;
        this.function = function;
    }

    private XYChart.Series<Number, Number> makeSeries() {
        XYChart.Series series = new XYChart.Series();
        double xVariable;
        for (xVariable = fromX; xVariable <= toX; xVariable += stepX) {
            series.getData().add(new XYChart.Data<>(xVariable, mathFunction(xVariable)));
        }
        return series;
    }

    private double mathFunction(double currentX) {

        String toCalculate;
        Double returnedValue = Double.NaN;
        toCalculate = function.replaceAll("x", String.valueOf(currentX));

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        toCalculate = toCalculate.replaceAll("sin", "Math.sin");
        toCalculate = toCalculate.replaceAll("cos", "Math.cos");
        toCalculate = toCalculate.replaceAll("log", "Math.log");

        try {
            returnedValue = Double.parseDouble(engine.eval(toCalculate).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        String savedString = String.format("%.3f %.3f\n", currentX, returnedValue);

        try {
            fw.write(savedString);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return returnedValue;
    }
}
