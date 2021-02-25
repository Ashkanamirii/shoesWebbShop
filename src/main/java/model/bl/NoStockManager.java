package model.bl;

import model.to.NoStock;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-23
 * Time:  17:21
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface NoStockManager {
    List<NoStock> getNoStockShoes() throws SQLException, IOException, ClassNotFoundException;
}
