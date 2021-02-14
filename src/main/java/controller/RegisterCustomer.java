package controller;

import connection.QueryExec;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modell.Customer;
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
    public TextField name;
    public TextField email;
    public TextField pswd;
    public TextField address;
    public TextField phone;
    public TextField country;
    public Label regLabel;


    public void initialize() {
        changeScene = new Utils();

        top.setOnAction(e -> {
            try {
                changeScene.changeScene("/home.fxml", regiPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        regBtn.setOnAction(e -> {
            if (checkInput()) {
                registerNewCustomer();
                System.out.println("register customer");
                regLabel.setText("YOU HAVE BEEN REGISTERED SUCCESSFULLY");
                regBtn.setText("Login");
                regBtn.setOnAction(event -> {
                    try {
                        changeScene.changeScene("/userProfile.fxml", regiPane);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            } else {
                System.out.println("Invalid");
            }
        });
    }

    private void registerNewCustomer() {
        Customer c = new Customer(name.getText(), phone.getText(), address.getText(),
                country.getText(), email.getText(), Utils.getMd5(pswd.getText()));
        QueryExec.insertIntoCustomer(c);
    }

    public boolean checkInput() {
        return true;
    }
}
