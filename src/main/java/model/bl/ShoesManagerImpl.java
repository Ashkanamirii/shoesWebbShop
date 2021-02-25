package model.bl;

import javafx.collections.ObservableList;
import model.da.ShoesDAO;
import model.da.ShoesDAOImpl;
import model.to.Shoes;

import java.io.IOException;
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
    public List<Shoes> getAllShoes() throws SQLException, IOException, ClassNotFoundException {
        ShoesDAO shoes = new ShoesDAOImpl();
        return shoes.select();
    }

    @Override
    public ObservableList<String> getColorList() throws SQLException, IOException, ClassNotFoundException {
        ShoesDAO shoes = new ShoesDAOImpl();
        return shoes.getColorList();
    }

// Test method for shoes objekt
    public List<Shoes> getAllShoes2() throws SQLException, IOException, ClassNotFoundException {
        ShoesDAO shoesDAO = new ShoesDAOImpl();
        CategoryManager categoryManager = new CategoryManagerImpl();
        List<Shoes> shoesList = shoesDAO.selectShoesWithBrand();//just get shoes
        for (Shoes s : shoesList) {
            s.setCategoriesNameList(categoryManager.getShoesCategory(s.getId()));
        }
        return shoesList;
    }

//    public static void main(String[] args) {
//        ShoesManagerImpl s = new ShoesManagerImpl();
//        try {
//             List<Shoes> shoesList = s.getAllShoes2();
//            for (Shoes shoes : shoesList ) {
//                System.out.println("shoes.getId() = " + shoes.getId());
//                System.out.println("--------");
//
//                System.out.println(shoes.getCategoriesP());
//                /*if (shoes.getCategories() !=null && shoes.getCategories().size()>0){
//                    for (Category c:shoes.getCategories()){
//                        System.out.println(c.getName());
//                    }
//                }*/
//
//            }
//            System.out.println();
//        } catch (SQLException | IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
