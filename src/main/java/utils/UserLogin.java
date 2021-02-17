package utils;

import connection.ConnectionDB;
import connection.QueryExec;
import modell.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  10:23
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class UserLogin {
    private static UserLogin instance = new UserLogin("default","default");
    private String email;
    private String password;
    private boolean isLogged;
    private Customer customer;

    private UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
        this.isLogged = checkPassword();
        //here we can run query
    }

    public static UserLogin getInstance(String email, String password) {
        //create an instance if there is no instance or is not logged in
        if (!instance.isLogged) {
            instance = new UserLogin(email, password);
        }
        return instance;
    }

    private boolean checkPassword() {
        return (QueryExec.validLogin(email,password));
    }

    public static boolean getIsLogged() {
         instance.checkPassword();
        return instance.isLogged;
    }

    public static Customer getCustomer() {
        return instance.customer;
    }
    public static void setCustomer(Customer customer) {
        instance.customer = customer;
    }
}
