package model.bl;

import model.to.Customer;
import model.to.History;

import java.io.IOException;
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
   void registerCustomer(Customer customer) throws SQLException, IOException, ClassNotFoundException;
   List<Customer> getAllCustomer() throws SQLException, IOException, ClassNotFoundException;
   Customer CheckValidCustomerByUserPswd(String email , String password) throws SQLException, IOException,
           ClassNotFoundException;
   List<History> customerHistory(int custId) throws SQLException, IOException, ClassNotFoundException;
   void update (Customer OldCustomer) throws SQLException, IOException, ClassNotFoundException;
}
