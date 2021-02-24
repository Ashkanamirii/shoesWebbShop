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




