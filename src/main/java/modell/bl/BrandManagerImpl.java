package modell.bl;

import modell.da.BrandDAOImpl;
import modell.to.Brand;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 2/22/2021
 * Time: 00:07
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class BrandManagerImpl implements BrandManager {

    @Override
    public List<Brand> getAllBrand() throws SQLException {
        BrandDAOImpl brand=new BrandDAOImpl();
        return brand.select();
    }
}
