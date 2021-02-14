package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  10:05
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class UserProfile {
    @FXML
    public Pane loginPane;
    public TextField email;
    public PasswordField passField;
    public AnchorPane mainPane;
    public Button login;
    public Label regLabel;
    private Utils util;
    //public Label loginMessageLabel;
    //public Button cancelButton;

    public void initialize() throws IOException {
        util = new Utils();
        login.setOnAction(e -> {
            try {
                if (checkInput()) {
                    UserLogin.getInstance(email.getText(), passField.getText());

                    if (UserLogin.getIsLogged()) {
                        util.changeScene("/webbshopPage.fxml", mainPane);

                    } else {
                        regLabel.setText("You must be register first");
                        login.setText("Register");
                        login.setOnAction(event -> {
                            try {
                                System.out.println("You must be register first");
                                util.changeScene("/registerCustomer.fxml", mainPane);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        });
                    }
                } else {
                    System.out.println("Invalid Email or Password");
                    regLabel.setText("You must be register first");
                    login.setText("Login as guest");
                    login.setOnAction(event -> {
                        try {
                            System.out.println("login as guest");
                            util.changeScene("/webbshopPage.fxml", mainPane);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    private boolean checkInput() {
        return !passField.getText().isBlank() && !passField.getText().isBlank();
    }

//    public void validLogin() {
//
//        Connection con = new ConnectionDB().getConnection();
//        String verifyLogin = "SELECT count(1) FROM customer WHERE " +
//                "email = '" + email.getText() + "' AND pswd =md5('" + passField.getText() + "');";
//        try {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(verifyLogin);
//
//            while (rs.next()) {
//                if (rs.getInt(1) == 1) {
//                    //loginMessageLabel.setText("Congratulation");
//                    String customerQuery = "SELECT * FROM customer WHERE email = '" + email.getText() + "';";
//                    QueryExec.customerInfo(customerQuery); // Det här retunerar en customer
//                    //TODO: Visa customer info
//                } else {
//                    //loginMessageLabel.setText("Invalid login. please try again");
//                    //TODO: visa register panel
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            e.getCause();
//        }
//    }
}
