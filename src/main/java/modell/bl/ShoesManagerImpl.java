package modell.bl;

import javafx.collections.ObservableList;
import modell.da.ShoesDAO;
import modell.da.ShoesDAOImpl;
import modell.to.Shoes;

import java.sql.ResultSet;
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

    @Override
    public ObservableList<String> getColorList() throws SQLException {
        ShoesDAO shoes=new ShoesDAOImpl();
        return shoes.getColorList();
    }


    public List<Shoes> getAllShoes2(){
        ShoesDAO shoesDAO = new ShoesDAOImpl();
        CategoryManager categoryManager= new CategoryManagerImpl();

        List<Shoes> shoesList=shoesDAO.select();//just get shoes

        for (Shoes s : shoesList){

            s.setCategories(categoryManager.getShoesCategory(s.getId()));

        }

    }

}
