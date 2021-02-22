package modell.bl;

import modell.da.OrderLineItemDAOImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-22
 * Time:  13:24
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrderLineItemManagerImpl implements OrderLineItemManager{
    @Override
    public void getAddTOCart(int customerId, int orderId, int shoesId, int quantity, int status) throws SQLException {
        OrderLineItemDAOImpl orderLineItemDAO = new OrderLineItemDAOImpl();
        orderLineItemDAO.addTOCart(customerId,orderId,shoesId,quantity,status);
    }

    @Override
    public List<String> getInvoice(int orderId) throws SQLException {
        OrderLineItemDAOImpl orderLineItemDAO= new OrderLineItemDAOImpl();
       return orderLineItemDAO.invoice(orderId);
    }
}