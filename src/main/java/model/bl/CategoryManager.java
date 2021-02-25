package model.bl;

import model.to.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 2/22/2021
 * Time: 09:57
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface CategoryManager {
    List<Category> getAllCategory() throws SQLException, IOException, ClassNotFoundException;
    Category getCategoryById (int categoryId) throws SQLException, IOException, ClassNotFoundException;
    List<Category> getCategoryListByShoesId(int shoesId) throws SQLException, IOException, ClassNotFoundException;
    List<Category> getShoesCategory(int id) throws SQLException, IOException, ClassNotFoundException;
}
