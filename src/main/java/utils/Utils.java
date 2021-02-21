package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        primaryStage.setWidth(((Pane) primaryStage.getScene().getRoot()).getPrefWidth());
        primaryStage.setHeight(((Pane) primaryStage.getScene().getRoot()).getPrefHeight());
    }

    public static void md5Hashing(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();
        // You can choose ByteToHex instead of DataTypeConverter
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        //----------------
        //MD5 using Apache Commons
        String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
    }

    public static String getMd5(String password) {
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

    public static String currentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
