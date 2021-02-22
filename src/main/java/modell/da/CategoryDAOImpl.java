package modell.da;

import connection.ConnectionDB;
import modell.to.Brand;
import modell.to.Category;
import modell.to.Shoes;

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

        List<Category> categories =new ArrayList<>();
        preparedStatement=connection.prepareStatement("SELECT * FROM category;");
        ResultSet resultSet=preparedStatement.executeQuery();

        while(resultSet.next()){
            categories.add(new Category(resultSet.getInt("id"),
                    resultSet.getString("name"),select(resultSet.getString("name"))));
        }
        close();
        return categories;
    }

    @Override
    public String getCategoryNameById(int categoryId) throws SQLException {
        String categoryName="";
        CallableStatement call=connection.prepareCall(" select getCategoryNameById(?) as categoryName;");
        call.setInt(1,categoryId);
        ResultSet rs=call.executeQuery();
        rs.next();
        categoryName=rs.getString(1);
        close();
        return categoryName;
    }


    @Override
    public String getCategoryIdsByShoesId(int shoesId) throws SQLException {
        String categoryName="";
        CallableStatement call=connection.prepareCall(" select getCategoryIdsByShoesId(?) as categoryIds;");
        call.setInt(1,shoesId);
        ResultSet rs=call.executeQuery();
        rs.next();
        categoryName=rs.getString(1);
        call.close();
        connection.close();
        return categoryName;
    }

    @Override
    public List<Shoes> select(String categoryName) throws SQLException {
        List<Shoes> shoesList = new ArrayList<>();
       PreparedStatement preparedStatement = connection.prepareStatement(" select * from shoes sh \n" +
                "                join brand b on b.id = sh.FK_brand_id\n" +
                "                left join shoes_category shc on shc.FK_shoes_id = sh.id\n" +
                "                join category c on c.id = shc.FK_category_id\n" +
                "                \n" +
                "                where c.name = ?;");

        preparedStatement.setString(1,categoryName);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){
            shoesList.add(new Shoes(rs.getInt(1),rs.getInt(2),rs.getInt(3),
                    new Brand(rs.getInt(4),rs.getString(11)),rs.getString(5),
                    rs.getDouble(6),rs.getInt(10)));
           // c = new Category(rs.getInt(18),rs.getString(19),shoesList);
        }
        preparedStatement.close();

        return shoesList;
    }
//    public List<Category> selectCategoryAndShoes() throws SQLException {
//        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
//        List<Shoes> shoesList = new ArrayList<>();
//        List<Category> c = new ArrayList<>();
//        Category c2 = null;
//        preparedStatement = connection.prepareStatement(" select * from shoes sh \n" +
//                "                join brand b on b.id = sh.FK_brand_id\n" +
//                "                left join shoes_category shc on shc.FK_shoes_id = sh.id\n" +
//                "                join category c on c.id = shc.FK_category_id\n" +
//                "                ;");
//
//        ResultSet rs = preparedStatement.executeQuery();
//
//        while (rs.next()){
//
////            shoesList.add(new Shoes(rs.getInt(1),rs.getInt(2),rs.getInt(3),
////                    new Brand(rs.getInt(4),rs.getString(11)),rs.getString(5),
////                    rs.getDouble(6),rs.getInt(10)));
//            c.add(new Category(rs.getInt(18),rs.getString(19),
//                    categoryDAO.select(rs.getString(19))));
//        }
//        close();
//        return c;
//    }

    public void close() throws SQLException {
        preparedStatement.close();
        connection.close();
    }
}
