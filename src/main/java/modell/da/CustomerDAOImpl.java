package modell.da;

import connection.ConnectionDB;
import modell.to.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:27
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public CustomerDAOImpl() {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public List<Customer> select() throws SQLException {

        List<Customer> custResult = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM customer");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setPhoneNumber(resultSet.getString("phone"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCountry(resultSet.getString("country"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPswd(resultSet.getString("pswd"));
            custResult.add(customer);
        }
        close();
        return custResult;
    }

    @Override
    public void insert(Customer c) throws SQLException {

        preparedStatement = connection.prepareStatement("INSERT INTO " +
                "customer (name, phone, address, country, email, pswd) "
                + "VALUES (?,?,?,?,?,?);");
        preparedStatement.setString(1, c.getName());
        preparedStatement.setString(2, c.getPhoneNumber());
        preparedStatement.setString(3, c.getAddress());
        preparedStatement.setString(4, c.getCountry());
        preparedStatement.setString(5, c.getEmail());
        preparedStatement.setString(6, c.getPswd());
        int resultOfInsert = preparedStatement.executeUpdate();
        if (resultOfInsert == 1)
            System.out.println("***Insert customer into database****");
        else
            System.out.println("ERROR-->Insert customer into database");

        close();
    }

    @Override
    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id = ?;");
        preparedStatement.setInt(1, id);

        int result = preparedStatement.executeUpdate();
        if (result == 1)
            System.out.println("***DELETE customer from database****");
        else
            System.out.println("ERROR-->DELETE customer from database");
        close();
    }

    @Override
    public void update(Customer customer) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE customer set " +
                "id = ?,name = ?, phone = ?, address = ?, country=?, email = ?,pswd = ?" +
                "WHERE id = ? ;");
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getPhoneNumber());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getCountry());
        preparedStatement.setString(6, customer.getEmail());
        preparedStatement.setString(7, customer.getPswd());
        preparedStatement.setInt(8, customer.getId());
        int result = preparedStatement.executeUpdate();
        if (result == 1)
            System.out.println("***UPDATE customer from database****");
        else
            System.out.println("ERROR-->UPDATE customer from database");
        close();
    }

    public Customer getOneCustomerByInfo(String email, String password) throws SQLException {
        Customer customer = null;
        preparedStatement = connection.prepareStatement
                ("select * from customer where email =? and pswd = md5(?);");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setPhoneNumber(resultSet.getString("phone"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCountry(resultSet.getString("country"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPswd(resultSet.getString("pswd"));
        }
        return customer;
    }

    @Override
    public List<String> customerHistory(int custId) throws SQLException {
        List<String> cHistory = null;
        CallableStatement callableStatement = connection.prepareCall("call customerHistory(?)");
        callableStatement.setInt(1, custId);

        ResultSet resultSet = callableStatement.executeQuery();
        
        while (resultSet.next()){
            cHistory = new ArrayList<>();
            cHistory.add(String.valueOf(callableStatement.getInt(1)));
            cHistory.add(String.valueOf(callableStatement.getDate(2)));
            cHistory.add(String.valueOf(callableStatement.getInt(3)));
            cHistory.add(String.valueOf(callableStatement.getDouble(4)));
            cHistory.add(callableStatement.getString(5));
            cHistory.add(String.valueOf(callableStatement.getInt(6)));
            cHistory.add(callableStatement.getString(7));
            cHistory.add(callableStatement.getString(8));
            cHistory.add(String.valueOf(callableStatement.getDouble(9)));
        }

        callableStatement.close();
        connection.close();
        return cHistory;
    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}