package modell.bl;


import modell.to.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Miwa Guhrés
 * Date: 2021-02-22
 * Time: 15:37
 * Project: guiDB
 * Copyright: MIT
 */
public interface OrdersManager {
    List<Orders> getAllOrders() throws SQLException;
    void generateOrder(Orders order) throws SQLException;
    List<Orders> getOrdersByCustomer(int login_customerId) throws SQLException;

}
