package modell.da;

import connection.ConnectionDB;
import modell.to.Brand;
import modell.to.Shoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ShoesDAOImpl() {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public Shoes getShoesById(int shoesId) throws SQLException {
       /*
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
                    brand,
                    rs.getString("color"),
                    (int) rs.getDouble("price"),
                    rs.getInt("quantity"));
        }
        close();
        return shoes;

        */
        return null;
    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
