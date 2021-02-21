package modell.da;

import connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:28
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrderLineItemDAOImpl implements OrderLineItemDAO {
    Connection connection;

    public OrderLineItemDAOImpl() {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public void addTOCart(int customerId, int orderId, int shoesId, int quantity, int status) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{call AddToCart(?,?,?,?,?)}");
        callableStatement.setInt(1, customerId);
        callableStatement.setInt(2, orderId);
        callableStatement.setInt(3, shoesId);
        callableStatement.setInt(4, quantity);
        callableStatement.setInt(5, status);

        callableStatement.execute();
        callableStatement.close();

        connection.close();


    }
}
