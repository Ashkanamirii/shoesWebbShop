package model.da;

import model.to.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:17
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface CategoryDAO {
    List<Category> select() throws SQLException;
    String getCategoryNameById(int id) throws SQLException;
    String getCategoryIdsByShoesId(int shoesId) throws SQLException;
    List<Category> selectCategoryListByShoesId(int shoesId) throws SQLException;
}
