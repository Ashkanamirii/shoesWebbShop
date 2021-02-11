package utils;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  10:23
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class UserLogin {
    private static UserLogin instance=new UserLogin("default","default");
    private String userName;
    private String password;
    private boolean isLogged;

    private UserLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isLogged = checkPassword();
        //here we can run query
    }

    public static UserLogin getInstance(String userName, String password) {
        //create an instance if there is no instance or is not logged in
        if (!instance.isLogged) {
            instance = new UserLogin(userName, password);
        }
        return instance;
    }

    private boolean checkPassword() {

        return (password.equals("a"));//TODO fix it
    }

    public static boolean getIsLogged() {
        return instance.isLogged;
    }

}
