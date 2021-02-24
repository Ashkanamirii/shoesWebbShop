package modell.bl;

import modell.da.OrdersDAOImpl;

import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-24
 * Time:  15:44
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrderManagerImpl implements OrderManager {

    @Override
    public int getLastOrderId(int custId) throws SQLException {
        OrdersDAOImpl o = new OrdersDAOImpl();
        return o.selectLastOrderId(custId);
    }
}
