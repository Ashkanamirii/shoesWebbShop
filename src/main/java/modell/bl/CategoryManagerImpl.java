package modell.bl;

import modell.da.CategoryDAOImpl;
import modell.to.Category;
import modell.to.Shoes;

import javax.xml.catalog.CatalogManager;
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
        List<Integer>categoryIds;
        List<Category>categoryByShoes=new ArrayList<>();
        String categoryIdS=categories.getCategoryIdsByShoesId(shoesId); //this gives one shoes category string
        categoryIds=new ArrayList<>((Collection<? extends Integer>)Arrays.asList(categoryIdS.split(", ")).stream().mapToInt(Integer::parseInt));
        //
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

    @Override
    public List<Shoes> getAllShoesByCategoryName(String categoryName) throws SQLException {
        CategoryDAOImpl categories=new CategoryDAOImpl();
        return categories.select(categoryName);
    }

    public static void main(String[] args) {
      CategoryManagerImpl c = new CategoryManagerImpl();
        CategoryDAOImpl categories=new CategoryDAOImpl();
        try {

            List<Category> co = categories.selectCategoryAndShoes();
            System.out.println(co.get(0).getShoesList().size());

            for (Category b : co
                 ) {
                System.out.println(b.getName());
                //System.out.println(b.getShoesList().stream().map(s-> s.getBrand().getName().));
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
