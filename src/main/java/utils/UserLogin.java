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

    private UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
        this.isLogged = checkPassword(email,password);
        //here we can run query
    }

    public static UserLogin getInstance(String email, String password) {
        //create an instance if there is no instance or is not logged in
        if (!instance.isLogged) {
            instance = new UserLogin(email, password);
        }
        return instance;
    }

    private boolean checkPassword(String email, String password) {
        Connection con = new ConnectionDB().getConnection();
        String verifyLogin = "SELECT count(1) FROM customer WHERE " +
                "email = '" + email + "' AND pswd =md5('" + password + "');";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(verifyLogin);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    //loginMessageLabel.setText("Congratulation");
                    // Det här retunerar en customer
                    Customer c = QueryExec.customerInfo("SELECT * FROM customer WHERE email = '" + email + "';");
                    System.out.println(c.getName());
                    return true;
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
        return (password.equals("a"));//TODO fix it
    }

    public static boolean getIsLogged() {
        return instance.isLogged;
    }

}
