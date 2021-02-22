package utils;


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
    private static UserLogin instance = new UserLogin("default","default");
    private String email;
    private String password;
    private boolean isLogged;
    private Customer customer ;
    CustomerManagerImpl customerManager ;

    private UserLogin(String email, String password){
        this.customerManager = new CustomerManagerImpl();
        this.email = email;
        this.password = password;
        this.isLogged = checkPassword();
    }

    public static UserLogin UserLogin(String email, String password) {
        //create an instance if there is no instance or is not logged in
        if (instance.email.equalsIgnoreCase("default")) {
            instance = new UserLogin(email, password);
        }
        return instance;
    }

    private boolean checkPassword(){
        customer = customerManager.CheckValidCustomerByUserPswd(email,password);
        if (customer  == null){
            setLogged(false);
            return false;
        }
        else {
            setLogged(true);
            setCustomer(customer);
            return true;
        }
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

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}


//public class UserLogin {
//    private static UserLogin instance = new UserLogin("default","default");
//    private String email;
//    private String password;
//    private boolean isLogged;
//    private Customer customer;
//    CustomerManagerImpl customerManager = new CustomerManagerImpl();
//
//    private UserLogin(String email, String password){
//        this.email = email;
//        this.password = password;
//        this.isLogged = getIsLogged();
//        //here we can run query
//    }
//
//    public static UserLogin getInstance(String email, String password) {
//        //create an instance if there is no instance or is not logged in
//        if (!instance.isLogged) {
//            instance = new UserLogin(email, password);
//        }
//        return instance;
//    }
//
//    private boolean checkPassword(){
//        return (customerManager.CheckValidCustomerByUserPswd(email,password));
//    }
//
//    public static boolean getIsLogged(){
//        instance.checkPassword();
//        return instance.isLogged;
//    }
//
//    public static Customer getCustomer() {
//        return instance.customer;
//    }
//    public static void setCustomer(Customer customer) {
//        instance.customer = customer;
//    }
//}
