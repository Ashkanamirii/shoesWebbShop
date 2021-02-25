package model.bl;

import model.da.BrandDAOImpl;
import model.to.Brand;

import java.io.IOException;
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
    public List<Brand> getAllBrand() throws SQLException, IOException, ClassNotFoundException {
        BrandDAOImpl brand=new BrandDAOImpl();
        return brand.select();
    }
}
