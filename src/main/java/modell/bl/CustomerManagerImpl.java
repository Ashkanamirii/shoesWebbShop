package modell.bl;

import modell.da.CustomerDAOImpl;
import modell.to.Customer;
import utils.UserLogin;

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
    public void registerCustomer(Customer customer) throws SQLException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        c.insert(customer);
    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException {
        CustomerDAOImpl c = new CustomerDAOImpl();
        return c.select();
    }

    @Override
    public boolean CheckValidCustomerByUserPswd(String email, String password)  {
        CustomerDAOImpl c = new CustomerDAOImpl();
        try {
            Customer customer = c.getOneCustomerByInfo(email, password);
            UserLogin.setCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FALSSSSSS");
        }
        return true;
    }
}
