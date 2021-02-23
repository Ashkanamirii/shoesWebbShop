package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;


public class MyPagesSurvey {
    @FXML
    public AnchorPane myPagesSurveyPane;
    public Button logout;
    public Button goToShopping;
    public Button myPagesBtn;
    public Button ordersBtn;
    public Label loginL;
    //public Pane shoesDescription_surveyP;
    public Utils changeScene;


    public void initialize() {
        changeScene = new Utils();


        loginL.setText("You login as " + UserLogin.getCustomer().getName());

        logout.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        myPagesBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesHome.fxml", myPagesSurveyPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        ordersBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesOrders.fxml", myPagesSurveyPane);
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


