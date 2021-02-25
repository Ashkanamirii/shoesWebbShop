package model.bl;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-24
 * Time:  15:43
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface OrderManager {
    int getLastOrderId(int custId) throws SQLException, IOException, ClassNotFoundException;
}
