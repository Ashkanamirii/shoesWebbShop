package model.da;

import connection.ConnectionDB;
import model.to.NoStock;
import model.to.Shoes;

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
 * Time:  08:28
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class NoStockDAOImpl implements NoStockDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public NoStockDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new ConnectionDB().getConnection();
    }





    @Override
    public List<NoStock> select() throws SQLException {

        List<NoStock> noStocks = new ArrayList<>();
        ResultSet rs =null;
        preparedStatement = connection.prepareStatement("SELECT * FROM no_stock");
         rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Shoes shoes = new Shoes();
            shoes.setId(rs.getInt(2));
            noStocks.add(new NoStock(rs.getInt(1),shoes,rs.getString(3)));
        }
            close();
            return noStocks;
    }
    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
