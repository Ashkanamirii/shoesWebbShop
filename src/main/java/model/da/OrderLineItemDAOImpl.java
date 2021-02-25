package model.da;

import connection.ConnectionDB;
import model.to.Invoice;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:28
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrderLineItemDAOImpl implements OrderLineItemDAO {
    Connection connection;

    public OrderLineItemDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public int addTOCart(int customerId, int orderId, int shoesId, int quantity, int status) throws SQLException {

        CallableStatement callableStatement = connection.prepareCall("{call AddToCart(?,?,?,?,?,?)}");
        callableStatement.setInt(1, customerId);
        callableStatement.setInt(2, orderId);
        callableStatement.setInt(3, shoesId);
        callableStatement.setInt(4, quantity);
        callableStatement.setInt(5, status);
        callableStatement.registerOutParameter(6, Types.INTEGER);
        callableStatement.execute();

        int createdOrderId = callableStatement.getInt(6);

        callableStatement.close();

        connection.close();
        return createdOrderId;
    }

    @Override
    public List<Invoice> invoice(int orderId) throws SQLException {
        List<Invoice> invoiceList = new ArrayList<>();
        CallableStatement call = connection.prepareCall("call invoice(?)");
        call.setInt(1, orderId);
        ResultSet rs = call.executeQuery();
        while (rs.next()){
            invoiceList.add(new Invoice(rs.getInt(1)
            ,rs.getString(2)
            ,rs.getString(3)
            ,rs.getString(4)
            ,rs.getInt(5)
            ,rs.getInt(6)
            ,rs.getDouble(7)
            ,rs.getDouble(8)
            ,rs.getInt(9)));
        }
        call.close();
        connection.close();
        return invoiceList;
    }
}
