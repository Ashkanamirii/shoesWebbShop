package model.bl;

import model.da.CategoryDAOImpl;
import model.to.Category;

import java.io.IOException;
import java.sql.SQLException;
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
    public List<Category> getAllCategory() throws SQLException, IOException, ClassNotFoundException {
        CategoryDAOImpl category=new CategoryDAOImpl();
        return category.select();
    }
    @Override
    public Category getCategoryById(int categoryId) throws SQLException, IOException, ClassNotFoundException {
        CategoryDAOImpl category=new CategoryDAOImpl();
            return new Category(categoryId,category.getCategoryNameById(categoryId));
    }
    @Override
    public List<Category> getCategoryListByShoesId(int shoesId) throws SQLException, IOException, ClassNotFoundException {
        CategoryDAOImpl categories=new CategoryDAOImpl();
        List<Category>categoryByShoes;
        String categoryIdS=categories.getCategoryIdsByShoesId(shoesId);
        List<Integer>categoryIds= (Arrays.asList(categoryIdS.split(", "))).
                stream().map(Integer::parseInt).collect(Collectors.toList());
        categoryByShoes=categoryIds.stream().map(id-> {
            try {
                return new Category(id,categories.getCategoryNameById(id));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());


        return categoryByShoes;
    }

    @Override
    public List<Category> getShoesCategory(int id) throws SQLException, IOException, ClassNotFoundException {
        CategoryDAOImpl category=new CategoryDAOImpl();
        return category.selectCategoryListByShoesId(id);
    }
}
