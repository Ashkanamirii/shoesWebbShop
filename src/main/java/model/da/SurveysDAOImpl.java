package model.da;

import connection.ConnectionDB;
import model.to.Surveys;
import model.to.Comments;
//import utils.ShoesAverageGrade;
import utils.Utils;

import java.io.IOException;
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

    public SurveysDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection = new ConnectionDB().getConnection();
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
    public List<Comments> getCommentByShoesId(int shoesNr) throws SQLException {
        List<Comments> listOfComment =new ArrayList<>();
        PreparedStatement pStmt = connection.prepareStatement(
                "SELECT c.name as customer_name, comment from surveys s " +
                        "join customer c on c.id = s.FK_customer_id JOIN shoes sh on s.FK_shoes_id = sh.id " +
                        "where sh.shoes_number = ?");
        pStmt.setInt(1, shoesNr);
        ResultSet resultSet = pStmt.executeQuery();
        while (resultSet.next()) {
            listOfComment.add(new Comments((resultSet.getString(1)),resultSet.getString(2)));
        }
        pStmt.close();
        return listOfComment;
    }
    @Override
    public double getShoesAverageRate(int shoesId) throws SQLException {
        double avg = 0;
        CallableStatement call = connection.prepareCall("select getShoesAverageGrade(?)");
        call.setInt(1, shoesId);
        ResultSet rs = call.executeQuery();
        while (rs.next()) {
            avg = rs.getDouble(1);
        }
        return avg;
    }


    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
