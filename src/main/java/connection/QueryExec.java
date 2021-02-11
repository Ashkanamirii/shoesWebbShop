package connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modell.Shoes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:24
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class QueryExec {
    public static ObservableList<Shoes> returnList(String query){
        ObservableList<Shoes> list=FXCollections.observableArrayList();
        try{
            /*
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoes_workshop", "root", "root");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoes_workshop", "root", "root");*/
            Connection con=new ConnectionDB().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //TODO:build a method for each query, and use properties for log in.

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
            con.close();}
        catch(Exception e){
            System.out.println("return list error");
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<String> returnQueryToList(String query){
        ObservableList<String> list=FXCollections.observableArrayList();
        try{
            Connection con=new ConnectionDB().getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                list.add(rs.getString(1));
                rs.getConcurrency();
            }
            con.close();}
        catch(Exception e){
            System.out.println("return list error");
            e.printStackTrace();
        }
        return list;
    }
}
