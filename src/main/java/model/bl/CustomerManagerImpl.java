package model.bl;

import model.da.CustomerDAOImpl;
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
public class CustomerManagerImpl implements CustomerManager{
    @Override
    public void registerCustomer(Customer customer) throws SQLException, IOException, ClassNotFoundException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        c.insert(customer);
    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException, IOException, ClassNotFoundException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        return c.select();
    }

    @Override
    public Customer CheckValidCustomerByUserPswd(String email, String password)
            throws SQLException, IOException, ClassNotFoundException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        Customer customer = null;
        try {
             customer = c.getOneCustomerByInfo(email, password);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FALSSSSSS");
        }
        return customer;
    }

    @Override
    public List<History> customerHistory(int custId) throws SQLException, IOException, ClassNotFoundException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        return c.customerHistory(custId);
    }

    @Override
    public void update(Customer OldCustomer) throws SQLException, IOException, ClassNotFoundException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        c.update(OldCustomer);
    }
}
