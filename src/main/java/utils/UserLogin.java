package utils;

import connection.QueryExec;
import modell.bl.CustomerManager;
import modell.bl.CustomerManagerImpl;
import modell.to.Customer;

import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  10:23
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class UserLogin {
    private static UserLogin instance = new UserLogin("a","a");
    private String email;
    private String password;
    private boolean isLogged;
    private Customer customer;
    CustomerManagerImpl customerManager ;

    private UserLogin(String email, String password){
        this.customerManager = new CustomerManagerImpl();
        this.email = email;
        this.password = password;
        //this.isLogged = checkPassword();


        //here we can run query
    }

    public static UserLogin UserLogin(String email, String password) {
        //create an instance if there is no instance or is not logged in
        if (instance.email.equalsIgnoreCase("a")) {
            instance = new UserLogin(email, password);
        }
        return instance;
    }

    private boolean checkPassword(){
        return customerManager.CheckValidCustomerByUserPswd(email,password);
    }

    public static Customer getCustomer() {
        return instance.customer;
    }
    public static void setCustomer(Customer customer) {
        instance.customer = customer;
    }

    public static boolean getIsLogged() {
        return instance.isLogged;
    }
}
