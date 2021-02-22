package modell.da;

import connection.ConnectionDB;
import modell.to.Brand;
import modell.to.Category;

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
    public CategoryDAOImpl(){connection=new ConnectionDB().getConnection();}
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
      //connection.close();
        return categoryName;
    }
    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
