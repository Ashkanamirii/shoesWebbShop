package modell.da;

import modell.to.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:16
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface CustomerDAO {
    List<Customer> select() throws SQLException;
    void insert (Customer customer) throws SQLException;
    void delete (int id) throws SQLException;
    void update (Customer OldCustomer) throws SQLException;
    Customer getOneCustomerByInfo(String email, String password) throws SQLException;
    List<String> customerHistory(int custId) throws SQLException;

}
