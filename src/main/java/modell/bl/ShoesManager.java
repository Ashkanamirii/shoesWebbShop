package modell.bl;

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
public interface ShoesManager {
    List<Shoes> getAllShoes() throws SQLException;
}
