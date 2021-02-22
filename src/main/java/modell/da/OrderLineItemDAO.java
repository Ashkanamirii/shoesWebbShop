package modell.da;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:20
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface OrderLineItemDAO {
    void addTOCart( int customerId, int orderId , int shoesId ,int ordered_quantity ,
                    int status) throws SQLException;
    List<String> invoice (int orderId) throws SQLException;
    int lastOrderIdByStatus(int shoesId, int statusEnum) throws SQLException;
}
