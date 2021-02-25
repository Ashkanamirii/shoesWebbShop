package model.da;

import javafx.collections.ObservableList;
import model.to.Shoes;

import java.io.IOException;
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
    Shoes getShoesById(int id) throws SQLException, IOException, ClassNotFoundException;
    List<Shoes> select() throws SQLException, IOException, ClassNotFoundException;
    List<Shoes> selectShoesWithBrand() throws SQLException;
    ObservableList<String> getColorList() throws SQLException;
}
