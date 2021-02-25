package model.da;

import connection.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.bl.CategoryManager;
import model.bl.CategoryManagerImpl;
import model.to.Brand;
import model.to.Shoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
;
import java.util.*;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:29
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesDAOImpl implements ShoesDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    public ShoesDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new ConnectionDB().getConnection();
    }
    public ObservableList<String> getColorList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

            preparedStatement = connection.prepareStatement("select distinct color from shoes;");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
                rs.getConcurrency();
            }
            close();
        return list;
        }


    @Override
    public Shoes getShoesById(int shoesId) throws SQLException, IOException, ClassNotFoundException {
        CategoryManager categoryManager=new CategoryManagerImpl();
        Shoes shoes = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM shoes sh " +
                "JOIN brand b on b.id = sh.FK_brand_id  WHERE sh.id = ?");
        preparedStatement.setInt(1, shoesId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand(rs.getInt(10), rs.getString(11));
            shoes = new Shoes(rs.getInt("id"),
                    rs.getInt("size"),
                    rs.getInt("shoes_number"),
                    brand,categoryManager.getCategoryListByShoesId(rs.getInt("id")),
                    rs.getString("color"),
                     rs.getDouble("price"),
                    rs.getInt("quantity"));
        }
        close();
        return shoes;

    }


    public List<Shoes> select() throws SQLException, IOException, ClassNotFoundException {
        CategoryManager categoryManager=new CategoryManagerImpl();
        List<Shoes>shoesResult =new ArrayList<>();
        preparedStatement=connection.prepareStatement("with categories as(select FK_shoes_id,group_concat(c.name separator ', ') as category,group_concat(c.id separator ', ') as categoryIds\n" +
                "    from shoes_category\n" +
                "    join category c on c.id=FK_category_id\n" +
                "    group by FK_shoes_id)\n" +
                "    select shoes.id,size,shoes_number,FK_brand_id,br.name,categoryIds,category,color,price,quantity \n" +
                "from shoes \n" +
                "join brand br on br.id=shoes.FK_brand_id\n" +
                "left join categories cs on cs.FK_shoes_id=shoes.id \n" +
                "order by shoes.id;");
        ResultSet resultSet =preparedStatement.executeQuery();
        while(resultSet.next()){
            shoesResult.add(new Shoes(resultSet.getInt("id"),resultSet.getInt("size"),
                                resultSet.getInt("shoes_number"),
                                new Brand(resultSet.getInt("FK_brand_id"),resultSet.getString("br.name")),
                                categoryManager.getCategoryListByShoesId(resultSet.getInt("id")),
                                resultSet.getString("color"),resultSet.getDouble("price"),
                                resultSet.getInt("quantity")));
        }


        close();

        return shoesResult;
    }
    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }


    public List<Shoes> selectShoesWithBrand() throws SQLException {
        List<Shoes> shoesList = new ArrayList<>();
        Shoes shoes = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM shoes sh " +
                "JOIN brand b on b.id = sh.FK_brand_id");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Brand brand = new Brand(rs.getInt(10), rs.getString(11));
            shoesList.add(new Shoes(rs.getInt("id"),
                    rs.getInt("size"),
                    rs.getInt("shoes_number"),
                    brand,null,
                    rs.getString("color"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")));
        }
        close();
        return shoesList;
    }



}
