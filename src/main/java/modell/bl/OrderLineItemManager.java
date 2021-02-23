package modell.bl;

import utils.Invoice;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-22
 * Time:  13:24
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface OrderLineItemManager {
    void getAddTOCart(int customerId, int orderId, int shoesId, int quantity, int status) throws SQLException;
    int getOrderIdFromAddTOCart(int customerId, int orderId, int shoesId, int quantity, int status) throws SQLException;
    List<Invoice> getInvoice(int orderId) throws SQLException;
}
