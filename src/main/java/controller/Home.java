package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

import java.io.IOException;


public class Home {
    @FXML
    public AnchorPane homePane;
    public Button reg;
    public Button loginBtn;
    public Button guestBtn;
    public Utils changeScene;


    public void initialize() {
        changeScene = new Utils();
        loginBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/userProfile.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        guestBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        reg.setOnAction(e -> {
            try {
                changeScene.changeScene("/registerCustomer.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}



