package modell.da;

import modell.to.Shoes;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-21
 * Time:  08:17
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public interface ShoesDAO {
    Shoes getShoesById(int id) throws SQLException;
    List<Shoes> select() throws SQLException;
}
