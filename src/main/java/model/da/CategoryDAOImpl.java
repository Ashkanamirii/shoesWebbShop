package model.da;

import connection.ConnectionDB;
import model.to.Category;

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
public class CategoryDAOImpl implements CategoryDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public CategoryDAOImpl() throws SQLException, IOException, ClassNotFoundException {
        connection=new ConnectionDB().getConnection();
    }
    @Override
    public List<Category> select() throws SQLException {
        List<Category>brandResult=new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM category;");
        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            brandResult.add(new Category(resultSet.getInt("id"),resultSet.getString("name")));
        }
        close();

        return brandResult;
    }

    @Override
    public String getCategoryNameById(int categoryId) throws SQLException {
        String categoryName="";
        CallableStatement call=connection.prepareCall(" select getCategoryNameById(?) as categoryName;");
        call.setInt(1,categoryId);
        ResultSet rs=call.executeQuery();
        rs.next();
        categoryName=rs.getString(1);
        call.close();
        //connection.close();
        return categoryName;
    }


    @Override
    public String getCategoryIdsByShoesId(int shoesId) throws SQLException {
        String categoryName=null;

        CallableStatement call=connection.prepareCall(" select getCategoryIdsByShoesId(?) as categoryIds;");
        call.setInt(1,shoesId);

        ResultSet rs=call.executeQuery();

      while(rs.next()) {
          categoryName = rs.getString(1);
      }
        call.close();
        //connection.close(); // todo HAr öppnat det
        return categoryName;
    }

    @Override
    public List<Category> selectCategoryListByShoesId(int shoesId) throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * from category c " +
                "join shoes_category sc on c.id = sc.FK_category_id where sc.FK_shoes_id = ?");
        preparedStatement.setInt(1,shoesId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            categoryList.add(new Category(
            rs.getInt(1),rs.getString(2)));
        }
        close();
        return categoryList;
    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
