package controller;

import utils.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

import java.io.IOException;

/**
 * Created by Miwa Guhrés
 * Date: 2021-02-12
 * Time: 16:23
 * Project: guiDB
 * Copyright: MIT
 */
public class RegisterCustomer {

    @FXML
    public AnchorPane regiPane;
    public Button top;
    public Button regBtn;
    public Utils changeScene;


    public void initialize()  {
        changeScene = new Utils();

        top.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", regiPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });


        // Just nu om man klicka register(Button) flyttar det till webbshopPage.fxml
        // Men om man handlar skorna som en gäst och  gästen måste registrera medlemskap innan betalla.
        // Så viewen flyttar till kassan efter register view kanske
        regBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", regiPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });


    }
}
