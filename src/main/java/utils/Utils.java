package utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:54
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Utils {

    public void changeScene(String nextPane, Pane mainPane) throws IOException {
        String fxml = nextPane;
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        primaryStage.getScene().setRoot(pane);

        primaryStage.setTitle("Welcome to YOUR shoes web shop");
        primaryStage.setWidth(((Pane)primaryStage.getScene().getRoot()).getPrefWidth());
        primaryStage.setHeight(((Pane)primaryStage.getScene().getRoot()).getPrefHeight());
    }
}
