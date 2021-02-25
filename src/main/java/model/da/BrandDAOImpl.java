package model.da;

import connection.ConnectionDB;
import model.to.Brand;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:27
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class BrandDAOImpl implements BrandDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public BrandDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection=new ConnectionDB().getConnection();
    }
    @Override
    public List<Brand> select() throws SQLException {
        List<Brand>brandResult=new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM brand;");
        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            brandResult.add(new Brand(resultSet.getInt("id"),resultSet.getString("name")));
        }
        close();

        return brandResult;
    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
