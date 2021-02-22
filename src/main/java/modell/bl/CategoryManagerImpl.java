package modell.bl;

import modell.da.CategoryDAOImpl;
import modell.to.Category;

import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Hodei Eceiza
 * Date: 2/22/2021
 * Time: 09:56
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class CategoryManagerImpl implements CategoryManager {
    @Override
    public List<Category> getAllCategory() throws SQLException {
        CategoryDAOImpl category=new CategoryDAOImpl();

        return category.select();
    }
    @Override
    public Category getCategoryById(int categoryId) throws SQLException{
        CategoryDAOImpl category=new CategoryDAOImpl();
            return new Category(categoryId,category.getCategoryNameById(categoryId));


    }

    @Override
    public List<Category> getCategoryListByShoesId(int shoesId) throws SQLException {
        CategoryDAOImpl categories=new CategoryDAOImpl();

        List<Category>categoryByShoes=new ArrayList<>();
        String categoryIdS=categories.getCategoryIdsByShoesId(shoesId);

        List<Integer>categoryIds= (Arrays.asList(categoryIdS.split(", "))).stream().map(Integer::parseInt).collect(Collectors.toList());

        categoryByShoes=new ArrayList(Arrays.asList(categoryIds.stream().map(id-> {
            try {
                return new Category(id,categories.getCategoryNameById(id));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList())));


        return categoryByShoes;
    }
}
