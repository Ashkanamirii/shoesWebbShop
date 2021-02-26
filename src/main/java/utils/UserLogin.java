package utils;


import model.bl.CustomerManagerImpl;
import model.to.Customer;

import java.io.IOException;
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

    private UserLogin(String email, String password)  {
        this.customerManager = new CustomerManagerImpl();
        this.email = email;
        this.password = password;
        try {
            this.isLogged = checkPassword();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.out.println("Connection Eror,Login failed for Customer ---> Email :" + email);
        }
    }

    public static UserLogin UserLogin(String email, String password) {
        //create an instance if there is no instance or is not logged in
        if (instance.email.equalsIgnoreCase("default")) {
            instance = new UserLogin(email, password);
        }
        return instance;
    }
    public static UserLogin getInstance(){
        return instance;
    }
    private boolean checkPassword() throws SQLException, IOException, ClassNotFoundException {
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

    public void resetEmail(){
        this.email="default";
        this.password = "default";
    }
}



