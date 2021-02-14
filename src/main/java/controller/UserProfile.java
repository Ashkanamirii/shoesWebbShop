package controller;

import connection.ConnectionDB;
import connection.QueryExec;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public Button startB;
    public AnchorPane mainPane;
    private Utils util;
    //public Label loginMessageLabel;
    //public Button cancelButton;

    public void initialize() throws IOException {
        util = new Utils();
        //call query to check the password and the user name, respond if any is not ok go to main window
        startB.setOnAction(e -> {
            if (passField.getText().equals("a")) {
                try {
                    util.changeScene("/webbshopPage.fxml", mainPane);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

    }

    public void loginButtonOnAction(ActionEvent event) {

        if (!passField.getText().isBlank() && !passField.getText().isBlank()) {
            validLogin();
        } else {
            //loginMessageLabel.setText("Please enter username and password");
        }
    }

    //    public void cancelButtonAction(ActionEvent event) {
//        Stage stage = (Stage) cancelButton.getScene().getWindow();
//        stage.close();
//    }

    public void validLogin() {

        Connection con = new ConnectionDB().getConnection();
        String verifyLogin = "SELECT count(1) FROM customer WHERE " +
                "email = '" + email.getText() + "' AND pswd =md5('" + passField.getText() + "');";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(verifyLogin);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    //loginMessageLabel.setText("Congratulation");
                    String customerQuery = "SELECT * FROM customer WHERE email = '" + email.getText() + "';";
                    QueryExec.customerInfo(customerQuery); // Det här retunerar en customer
                    //TODO: Visa customer info
                } else {
                    //loginMessageLabel.setText("Invalid login. please try again");
                    //TODO: visa register panel
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void registerButtonAction(ActionEvent event) {
        // u.loadViews("register", loginMessageLabel);
    }

    public void mainMenu() {
        // u.loadViews("mainMenu", loginMessageLabel);
    }
}
