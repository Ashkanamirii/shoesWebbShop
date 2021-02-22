package modell.bl;

import modell.da.ShoesDAO;
import modell.da.ShoesDAOImpl;
import modell.to.Shoes;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 2/22/2021
 * Time: 12:13
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesManagerImpl implements ShoesManager {

    @Override
    public List<Shoes> getAllShoes() throws SQLException {
        ShoesDAO shoes=new ShoesDAOImpl();
        return shoes.select();
    }
}
