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
    requires org.apache.commons.codec;
    requires java.xml.bind;
    opens connection;
    opens controller;
    opens main;
    opens model.bl;
    opens model.da;
    opens model.to;
    opens utils;


}