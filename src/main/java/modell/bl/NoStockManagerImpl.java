package modell.bl;

import modell.da.NoStockDAOImpl;
import modell.to.NoStock;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-23
 * Time:  17:21
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class NoStockManagerImpl implements NoStockManager{
    @Override
    public List<NoStock> getNoStockShoes() throws SQLException {
        NoStockDAOImpl noStockDAO = new NoStockDAOImpl();
        noStockDAO.select();
        return noStockDAO.select();
    }
}
