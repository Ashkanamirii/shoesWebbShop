package modell.da;

import connection.ConnectionDB;
import modell.to.Customer;
import modell.to.Shoes;
import modell.to.Surveys;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:29
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class SurveysDAOImpl implements SurveysDAO {
    Connection connection;
    PreparedStatement preparedStatement;

    public SurveysDAOImpl() {
        connection = new ConnectionDB().getConnection();
    }

    @Override
    public List<Surveys> select() throws SQLException {
        return null;
    }

    @Override
    public void insert(Surveys surveys) throws SQLException {

        preparedStatement = connection.prepareStatement("INSERT INTO " +
                "surveys (comment,grade, issue_date,FK_shoes_id ,FK_customer_id) "
                + "VALUES (?,?,?,?,?);");
        preparedStatement.setString(1, surveys.getComment());
        preparedStatement.setString(2, surveys.getGrade());
        preparedStatement.setString(3, Utils.currentDate());
        preparedStatement.setInt(4, surveys.getShoesId().getId());
        preparedStatement.setInt(5, surveys.getCustomerId().getId());

        int resultOfInsert = preparedStatement.executeUpdate();
        if (resultOfInsert == 1)
            System.out.println("***Insert Surveys into database****");
        else
            System.out.println("ERROR-->Insert Surveys into database");

        close();

    }

    @Override
    public void setRate(int shoesId, int custId, int rate, String comment) throws SQLException {
        CallableStatement callableStatement = connection.prepareCall("call rate(?,?,?,?)");
        callableStatement.setInt(1, custId);
        callableStatement.setInt(2, shoesId);
        callableStatement.setInt(3, rate);
        callableStatement.setString(4, comment);

        int result = callableStatement.executeUpdate();
        if (result == 1)
            System.out.println("***Insert rate into database****");
        else
            System.out.println("ERROR-->Insert rate into database");
        callableStatement.close();
        connection.close();
    }

    @Override
    public List<String> productAverageRateView() throws SQLException {
        List<String> shoesAverageRateView = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * from product_average_rate");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            shoesAverageRateView.add(resultSet.getString("shoes_name"));
            shoesAverageRateView.add(String.valueOf(resultSet.getInt("shoesNumber")));
            shoesAverageRateView.add(String.valueOf(resultSet.getLong("Number_Of_Rating")));
            shoesAverageRateView.add(String.valueOf(resultSet.getDouble("Average_Rate")));
            shoesAverageRateView.add(resultSet.getString("Rate"));
        }
        close();
        return shoesAverageRateView;
    }

    @Override
    public double getShoesAverageRate(int shoesId) throws SQLException {
        double avg = 0;
        CallableStatement call = connection.prepareCall("select getShoesAverageGrade(?)");
        call.setInt(1,shoesId);
        ResultSet rs = call.executeQuery();
        while (rs.next()){
           avg =  rs.getDouble(1);
        }
        return avg;
    }


    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
