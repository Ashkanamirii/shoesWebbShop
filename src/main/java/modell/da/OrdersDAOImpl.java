package modell.da;

import connection.ConnectionDB;
import modell.to.Customer;
import modell.to.Orders;
import utils.Utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:29
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrdersDAOImpl implements OrdersDAO{
    Connection connection;
    PreparedStatement prsm;

    public OrdersDAOImpl(){
        connection =new ConnectionDB().getConnection();
    }

    @Override
    public List<Orders> select() throws SQLException {
        List<Orders> allOrders= new ArrayList<>();
        ResultSet rs=null;
        prsm = connection.prepareStatement("SELECT * FROM orders");
        rs = prsm.executeQuery();

        while (rs.next()){
            Customer customer = new Customer();
            customer.setId(rs.getInt(2));
            allOrders.add(new Orders(rs.getInt(1), customer,rs.getString(4)));
        }
        close();
        return allOrders;
    }

    public void insert (Orders o) throws SQLException {
        prsm= connection.prepareStatement("INSERT INTO " +
                "orders (FK_customer_id, created, order_date)"
                + "VALUES (?,?,?);");
        prsm.setInt(1, o.getCustomerId().getId());
        prsm.setString(2, Utils.currentDate());
        prsm.setString(3, o.getOrderDate());

        int resultOfInsert = prsm.executeUpdate();
        if (resultOfInsert == 1)
            System.out.println("***Insert an order into database****");
        else
            System.out.println("ERROR-->Insert an order into database");

        close();
    }

    //När behöver vi? (Vi kan ha tillgång till databas. Man får generellt inte ta bort data i orders tabel.)
    public void delete (int id) throws SQLException{
        prsm = connection.prepareStatement("DELETE FROM orders WHERE id =?;");
        prsm.setInt(1, id);

        int resultOfInsert = prsm.executeUpdate();
        if (resultOfInsert == 1)
            System.out.println("***Delete an order from database****");
        else
            System.out.println("ERROR-->Delete an order from database");

        close();
    }

    // När behöver vi? (Vi kan tillgång till databas.Man redigerar inte data i orders tabel)
    public void update(Orders o) throws SQLException {
        prsm = connection.prepareStatement("UPDATE orders set " +
                "id = ?,FK_customer_id = ?, created = ?, order_date = ? " +
                "WHERE id = ?;");
        prsm.setInt(1, o.getId());
        prsm.setInt(2, o.getCustomerId().getId());
        prsm.setString(3, Utils.currentDate());
        prsm.setString(4, o.getOrderDate());
        prsm.setInt(5, o.getId());

        int result = prsm.executeUpdate();
        if (result == 1)
            System.out.println("***UPDATE an order from database****");
        else
            System.out.println("ERROR-->UPDATE an order from database");
        close();
    }


    @Override
    public List<Orders> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Orders> ordersByCustomer= new ArrayList<>();
        Orders orders= null;
        prsm = connection.prepareStatement
                ("select * from orders where  FK_customer_id = ?;");
        prsm.setInt(1, orders.getCustomerId().getId());

        ResultSet rs = prsm.executeQuery();
        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt(2));
            ordersByCustomer.add(new Orders((rs.getInt(1)),customer, rs.getString(4)));
        }
        close();
        return ordersByCustomer;
    }


    public void close() throws SQLException {
        prsm.close();
        connection.close();
    }


}
