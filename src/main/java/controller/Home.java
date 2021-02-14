package controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.Utils;

import java.io.IOException;


public class Home {
    @FXML
    public AnchorPane homePane;
    public Button log;
    public Button reg;
    public Button loginBtn;
    public Button guestBtn;
    public Utils changeScene;


    public void initialize()  {
        changeScene = new Utils();

        loginBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/userProfile.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        log.setOnAction(e -> {
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




//Måste fixa fel Om vi har tid (On Action)
    // On Action fungerade inte
    // Jag testade att första view ska vara home.fxml
    // Main class -->
    // Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));
    // Om ma klicka t.ex LoginBtn kommer det massor med fel

/*
        public void goToLogin (ActionEvent e){
            try {
                changeScene.changeScene("/userProfile.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }


        public void goToWS (ActionEvent e){
            try {
                changeScene.changeScene("/webbshopPage.fxml", homePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

/*
        public void goToRegi (ActionEvent e)  {
            try {
                changeScene.changeScene("/XXXXX.fxml", mainPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

 */



