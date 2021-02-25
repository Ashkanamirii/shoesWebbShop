package model.da;

import model.to.NoStock;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:19
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface NoStockDAO {
    List<NoStock> select() throws SQLException;
}
