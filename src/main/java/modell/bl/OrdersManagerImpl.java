package modell.bl;

import modell.da.OrdersDAOImpl;
import modell.to.Orders;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by Miwa Guhrés
 * Date: 2021-02-22
 * Time: 15:38
 * Project: guiDB
 * Copyright: MIT
 */
public class OrdersManagerImpl implements OrdersManager{

    @Override
    public List<Orders> getAllOrders() throws SQLException {
        OrdersDAOImpl o = new OrdersDAOImpl();
        return o.select();
    }
    @Override
    public void generateOrder(Orders order) throws SQLException {
        OrdersDAOImpl o = new OrdersDAOImpl();
        o.insert(order);
    }

    @Override
    public List<Orders> getOrdersByCustomer(int login_customerId) throws SQLException {
        OrdersDAOImpl o = new OrdersDAOImpl();
        return o.getOrdersByCustomerId(login_customerId);
    }


}
