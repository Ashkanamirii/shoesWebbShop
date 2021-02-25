package model.da;

import connection.ConnectionDB;
import model.to.Customer;
import model.to.History;

import java.io.IOException;
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

    public CustomerDAOImpl() throws SQLException, IOException, ClassNotFoundException {
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
        if (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setPhoneNumber(resultSet.getString("phone"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCountry(resultSet.getString("country"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPswd(resultSet.getString("pswd"));

        }
        close();
        return customer;
    }

    @Override
    public List<History> customerHistory(int custId) throws SQLException {
        List<History> cHistory = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall("call customerHistory(?)");
        callableStatement.setInt(1, custId);

        ResultSet resultSet = callableStatement.executeQuery();
        
        while (resultSet.next()){
            cHistory.add(new History(resultSet.getInt(1)
            ,String.valueOf(resultSet.getDate(2))
            ,resultSet.getInt(3)
            ,resultSet.getDouble(4)
            ,resultSet.getString(5)
            ,resultSet.getInt(6)
            ,resultSet.getString(7)
            ,resultSet.getString(8)
            ,resultSet.getDouble(9)
            ,resultSet.getInt(10)));
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
