package utils;
import connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modell.Shoes;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:54
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Utils {

    public void changeScene(String nextPane, Pane mainPane) throws IOException {
        String fxml = nextPane;
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage primaryStage = (Stage) mainPane.getScene().getWindow();
        primaryStage.getScene().setRoot(pane);

        primaryStage.setTitle("Welcome to YOUR shoes web shop");
        primaryStage.setWidth(((Pane)primaryStage.getScene().getRoot()).getPrefWidth());
        primaryStage.setHeight(((Pane)primaryStage.getScene().getRoot()).getPrefHeight());
    }
    public void loadViews(String FXMLFileName, Label logOutLabel) {
        try {
            // Detta är för att stänga föregående scene och ladda en ny
            Stage stage = (Stage) logOutLabel.getScene().getWindow();
            stage.close();

            Parent userLogin = FXMLLoader.load(getClass().getClassLoader()
                    .getResource(FXMLFileName + ".fxml"));
            stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(userLogin));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void md5 (String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            //String hashPswd = DatatypeConvertor
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


        public static String getMd5(String password)
        {
            try {

                // Static getInstance method is called with hashing MD5
                MessageDigest md = MessageDigest.getInstance("MD5");

                // digest() method is called to calculate message digest
                //  of an input digest() return array of byte
                byte[] messageDigest = md.digest(password.getBytes());

                // Convert byte array into signum representation
                BigInteger no = new BigInteger(1, messageDigest);

                // Convert message digest into hex value
                String hashtext = no.toString(16);
                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
            }

            // For specifying wrong message digest algorithms
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
}
