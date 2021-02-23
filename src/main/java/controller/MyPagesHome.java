package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;


public class MyPagesHome {
    @FXML
    public AnchorPane myPagesHomePane;
    public Button logout;
    public Button goToShopping;
    public Button ordersBtn;
    public Button surveyBtn;
    public Text cutomerName;
    public Utils changeScene;
    public VBox surveyBtnBox;


    public void initialize() {
        changeScene = new Utils();


       cutomerName.setText( UserLogin.getCustomer().getName() );

        logout.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", myPagesHomePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("/webbshopPage.fxml", myPagesHomePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        ordersBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesOrders.fxml", myPagesHomePane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        surveyBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("/myPagesSurvey.fxml", myPagesHomePane);
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



