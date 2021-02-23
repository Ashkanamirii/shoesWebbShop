package modell.bl;

import modell.to.Customer;
import utils.History;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  09:46
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface CustomerManager {
   void registerCustomer(Customer customer) throws SQLException;
   List<Customer> getAllCustomer() throws SQLException;
   Customer CheckValidCustomerByUserPswd(String email , String password);
   List<History> customerHistory(int custId) throws SQLException;
}
