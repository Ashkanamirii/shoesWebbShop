package model.bl;

import model.to.Brand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 2/21/2021
 * Time: 23:09
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface BrandManager {
    List<Brand> getAllBrand() throws SQLException, IOException, ClassNotFoundException;
}
