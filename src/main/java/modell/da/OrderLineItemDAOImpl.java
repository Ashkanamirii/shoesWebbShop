package modell.da;

import connection.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
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

    @Override
    public List<String> invoice(int orderId) throws SQLException {
        List<String> invoiceList = new ArrayList<>();
        CallableStatement call = connection.prepareCall("call invoice(?)");
        call.setInt(1, orderId);
        ResultSet rs = call.executeQuery();
        while (rs.next()){
            invoiceList.add(String.valueOf(rs.getInt(1)));
            invoiceList.add(rs.getString(2));
            invoiceList.add(rs.getString(3));
            invoiceList.add(rs.getString(4));
            invoiceList.add(String.valueOf(rs.getInt(5)));
            invoiceList.add(String.valueOf(rs.getInt(6)));
            invoiceList.add(String.valueOf(rs.getDouble(7)));
            invoiceList.add(String.valueOf(rs.getDouble(8)));
        }
        call.close();
        connection.close();
        return invoiceList;
    }


}
