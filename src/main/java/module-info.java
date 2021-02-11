/**
 * Created by Ashkan Amiri
 * Date:  2021-02-05
 * Time:  12:15
 * Project: shoesWebbShop
 * Copyright: MIT
 */module shoesWebbShop {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires org.controlsfx.controls;
    //opens view;
    opens connection;
    opens controller;
    opens main;
    opens modell;
    opens utils;


}