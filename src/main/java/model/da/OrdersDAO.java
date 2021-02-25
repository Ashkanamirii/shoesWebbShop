package model.da;

import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:20
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface OrdersDAO {
    int selectLastOrderId(int custId) throws SQLException;
}
