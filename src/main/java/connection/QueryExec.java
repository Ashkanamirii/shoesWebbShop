package connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modell.Customer;
import modell.Shoes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:24
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class QueryExec {

    public static ObservableList<Shoes> returnList(String query) {
        ObservableList<Shoes> list = FXCollections.observableArrayList();
        try {
            Connection con = new ConnectionDB().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(new Shoes(rs.getInt("id"),
                        rs.getInt("size"),
                        rs.getInt("shoes_number"),
                        rs.getString("FK_brand_id"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        rs.getInt("quantity")));
                rs.getConcurrency();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("return list error");
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<String> returnQueryToList(String query) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            Connection con = new ConnectionDB().getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                list.add(rs.getString(1));
                rs.getConcurrency();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("return list error");
            e.printStackTrace();
        }
        return list;
    }

    public static Customer customerInfo(String query) {
        Customer customer = null;
        try {
            Connection con = new ConnectionDB().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                customer = new Customer(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getString("email"),
                        rs.getString("pswd"));
                rs.getConcurrency();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("return list error");
            e.printStackTrace();
        }
        return customer;
    }

    public static void insertIntoCustomer(Customer c){

            try
            {
                Connection con = new ConnectionDB().getConnection();

                Statement st = con.createStatement();

                // note that i'm leaving "date_created" out of this insert statement
                st.executeUpdate("INSERT INTO customer (name, phone, address, country, email, pswd) "
                        +"VALUES ('"+c.getName()+"','"+c.getPhoneNumber()+"','"+c.getAddress()+
                        "','"+c.getCountry()+"','"+c.getEmail()+"','"+c.getPswd()+"')");

                con.close();
            }
            catch (Exception e)
            {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

    }

    public static List<String> returnQueryToListnew(String query) {
        List<String> c = new ArrayList<>();
        try {
            Connection con = new ConnectionDB().getConnection();
            Statement stmt = null;
            stmt = con.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(query);
            while (true) {
                if (!rs.next()) break;
                int numColumns = 0;
                numColumns = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    // Column numbers start at 1.
                    // Also there are many methods on the result set to return
                    //  the column as a particular type. Refer to the Sun documentation
                    //  for the list of valid conversions.
                    System.out.println("COLUMN " + i + " = " + rs.getObject(i));
                    c.add(rs.getString(i));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }
}