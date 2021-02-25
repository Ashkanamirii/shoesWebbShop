package model.da;

import connection.ConnectionDB;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:29
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrdersDAOImpl implements  OrdersDAO{
    Connection connection;
    public OrdersDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public int selectLastOrderId(int custId) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("{call getOrderIDforInvoice(?,?)}");
        callableStatement.setInt(2, custId);

        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();

        int createdOrderId = callableStatement.getInt(1);

        callableStatement.close();
        connection.close();
        return createdOrderId;
    }
}
