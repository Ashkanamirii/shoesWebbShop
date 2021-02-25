package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    public Button top;
    public Button login;
    public Button myPages;
    public Label regLabel;
    public Button registerButton;
    public Label loginTextField;
    public Label registerTextField;
    public Label myPageTextField;
    public ImageView registerImage;
    private Utils util;

    public void initialize() throws IOException {
        util = new Utils();

        top.setOnAction(e -> {
            try {
                util.changeScene("/home.fxml", mainPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
       // registerButton.setDisable(false);
        registerImage.setVisible(false);
        registerTextField.setVisible(false);
        registerButton.setVisible(false);
        login.disableProperty().bind(email.textProperty().isEmpty().or(passField.textProperty().isEmpty()));

        login.setOnAction(e -> {
            try {
                    UserLogin.UserLogin(email.getText(), passField.getText());
                    if (UserLogin.getIsLogged()) {
                        util.changeScene("/webbshopPage.fxml", mainPane);
                    } else {
                        registerButton.setVisible(true);
                        registerImage.setVisible(true);
                        registerTextField.setVisible(true);
                        login.setText("Login as guest");
                        login.setOnAction(l -> {
                            try {
                                util.changeScene("SHOP");
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        });
                    }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        myPages.disableProperty().bind(email.textProperty().isEmpty().or(passField.textProperty().isEmpty()));
        myPages.setOnAction(e -> {
            try {
                    UserLogin.UserLogin(email.getText(), passField.getText());
                    if (UserLogin.getIsLogged()) {
                        util.changeScene("/myPagesHome.fxml", mainPane);
                    } else {
                        registerButton.setVisible(true);
                        registerImage.setVisible(true);
                        registerTextField.setVisible(true);
                        login.setText("Login as guest");
                        login.setOnAction(l -> {
                            try {
                                util.changeScene("SHOP");
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
}
