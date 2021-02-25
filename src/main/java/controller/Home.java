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
        changeScene = new Utils(homePane);
        loginBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("LOG_IN");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        guestBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("SHOP");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        reg.setOnAction(e -> {
            try {
                changeScene.changeScene("REGISTER");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}



