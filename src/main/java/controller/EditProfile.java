package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.bl.CustomerManagerImpl;
import model.da.CustomerDAOImpl;
import model.to.Customer;
import utils.UserLogin;
import utils.Utils;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-25
 * Time:  11:53
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class EditProfile {
    public TextField name;
    public TextField email;
    public TextField pswd;
    public TextField address;
    public Button updateBtn;
    public TextField country;
    public TextField phone;
    public VBox surveyBtnBox;
    public Button myPagesBtn;
    public Button ordersBtn;
    public Button editUserProfileBtn;
    public Label loginL;
    public Button logout;
    public Label regLabel;
    public Button goToShopping;
    public AnchorPane regiPane;
    Customer updatedCustomer;
    Utils utils;
CustomerManagerImpl customerManager=new CustomerManagerImpl();

    public void initialize(){

        utils = new Utils(regiPane);


    name.setText(UserLogin.getCustomer().getName());
    email.setText(UserLogin.getCustomer().getEmail());
    pswd.setPromptText(UserLogin.getCustomer().getEmail());
    address.setText(UserLogin.getCustomer().getAddress());
    country.setText(UserLogin.getCustomer().getCountry());
    phone.setText(UserLogin.getCustomer().getPhoneNumber());
    email.setEditable(false);

    updateBtn.setOnAction(e->{
        updatedCustomer = new Customer(UserLogin.getCustomer().getId(),
                        name.getText(),
                        phone.getText(),
                        address.getText(),
                        country.getText(),
                        email.getText(),
                Utils.getMd5(pswd.getText()));

        try {
            customerManager.update(updatedCustomer);
        } catch (SQLException | ClassNotFoundException | IOException sqlException) {
            sqlException.printStackTrace();
        }
        showAlert();
        UserLogin.setCustomer(updatedCustomer);

    });


        logout.setOnAction(e -> {
            try {
                utils.changeScene("HOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        myPagesBtn.setOnAction(e -> {
            try {
                utils.changeScene("MYPAGESHOME");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        ordersBtn.setOnAction(e -> {
            try {
                utils.changeScene("ORDERS");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        goToShopping.setOnAction(e -> {
            try {
                utils.changeScene("SHOP");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
}
public void showAlert(){
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("User profile changes updated");
        a.setContentText("Updated everything to: "+"\n" + updatedCustomer.getName()+"\n"+
                 "\n"+updatedCustomer.getAddress()
                + "\n"+updatedCustomer.getCountry()
                + "\n"+updatedCustomer.getPhoneNumber()
                + "\n"+updatedCustomer.getPswd());
        a.showAndWait();
}

}
