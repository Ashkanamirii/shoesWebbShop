package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    public TextField nameField;
    public PasswordField passField;
    public Button startB;
    public AnchorPane mainPane;
    private Utils changeScene;
    public void initialize() throws IOException {
        changeScene=new Utils();


        //call query to check the password and the user name, respond if any is not ok go to main window
        startB.setOnAction(e->{if(passField.getText().equals("a")) {
            try {
                changeScene.changeScene("/main.fxml",mainPane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        });

    }
}
