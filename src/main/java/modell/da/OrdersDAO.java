package modell.da;

import modell.to.Customer;
import modell.to.Orders;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:20
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface OrdersDAO {
    List<Orders> select()throws SQLException;
    void insert (Orders order) throws SQLException;
    void delete (int id) throws SQLException;
    void update (Orders OldOrder) throws SQLException;
    List <Orders> getOrdersByCustomerId(int customerId) throws SQLException;
}
