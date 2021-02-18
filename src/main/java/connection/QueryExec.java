package connection;

import com.mysql.cj.jdbc.CallableStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modell.Customer;
import modell.Shoes;
import utils.UserLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:24
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class QueryExec {

    public static ObservableList<Shoes> shoesListInfo(String query) {
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

    public static void insertIntoCustomer(Customer c) {

        try {
            Connection con = new ConnectionDB().getConnection();

            Statement st = con.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO customer (name, phone, address, country, email, pswd) "
                    + "VALUES ('" + c.getName() + "','" + c.getPhoneNumber() + "','" + c.getAddress() +
                    "','" + c.getCountry() + "','" + c.getEmail() + "','" + c.getPswd() + "')");

            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public static ObservableList<Shoes>getShoesList(){
       return QueryExec.shoesListInfo("SELECT shoes.id as id,size,shoes_number," +
                "br.name as FK_brand_id,color,price,quantity\n" +
                " FROM shoes\n" +
                " join brand br on br.id =FK_brand_id;");
    }
    public static ObservableList<String> getColorsList(){
        return QueryExec.returnQueryToList("select distinct color from shoes;");
    }
    public static ObservableList<String> getCategoriesList(){
        return QueryExec.returnQueryToList("select name from category");
    }
    public static ObservableList<String> getBrandList(){
        return QueryExec.returnQueryToList("select distinct br.name from shoes join brand br on br.id=FK_brand_id");
    }

    public static boolean validLogin(String email, String passField) {
        try {
        Connection con = new ConnectionDB().getConnection();
        String verifyLogin = "SELECT count(1) FROM customer WHERE " +
                "email = '" + email + "' AND pswd =md5('" + passField + "');";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(verifyLogin);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    String customerQuery = "SELECT * FROM customer WHERE email = '" + email + "';";
                    Customer c = QueryExec.customerInfo(customerQuery);
                    UserLogin.setCustomer(c);
                    return true;
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    public static void addToCart(int customerId, Integer orderId, int shoesId, int quantity, boolean areReturned){
        Connection con = new ConnectionDB().getConnection();
        try {
            CallableStatement callableStatement= (CallableStatement) con.prepareCall("{call AddToCart(?,?,?,?,?)}");
        callableStatement.setInt(1,customerId);
        callableStatement.setInt(2,orderId);
        callableStatement.setInt(3,shoesId);
        callableStatement.setInt(4,quantity);
        callableStatement.setBoolean(5,areReturned);

        callableStatement.execute();
        callableStatement.close();

            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public static int getCreatedOrder(int customerId, Integer orderId){
        Connection con = new ConnectionDB().getConnection();
        try {
            CallableStatement callableStatement= (CallableStatement) con.prepareCall("{call getNewOrderId(?,?)}");
            callableStatement.setInt(1,orderId);
            callableStatement.setInt(2,customerId);


            callableStatement.execute();
            orderId=callableStatement.getInt(1);
            callableStatement.close();

            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orderId;
    }

}