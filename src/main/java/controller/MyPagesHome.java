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
    public Text cutomerName;
    public VBox surveyBtnBox;
    public Button editUserProfileBtn;
    public Utils changeScene;
    public void initialize() {
        changeScene = new Utils(myPagesHomePane);
       cutomerName.setText( UserLogin.getCustomer().getName() );

       logout.setOnAction(e -> {
            UserLogin.getInstance().setLogged(false);
            try {
                changeScene.changeScene("HOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editUserProfileBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("EDITPROFILE");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        goToShopping.setOnAction(e -> {
            try {
                changeScene.changeScene("SHOP");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        ordersBtn.setOnAction(e -> {
            try {
                changeScene.changeScene("ORDERS");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }
}




