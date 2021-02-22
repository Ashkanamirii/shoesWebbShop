package modell.bl;

import modell.to.Category;
import modell.to.Shoes;

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
    List<Category> getAllCategory() throws SQLException;
    Category getCategoryById (int categoryId) throws SQLException;
    List<Category> getCategoryListByShoesId(int shoesId) throws SQLException;
    List<Shoes> getAllShoesByCategoryName(String categoryName) throws SQLException;
}
