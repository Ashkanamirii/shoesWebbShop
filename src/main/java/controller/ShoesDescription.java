package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import modell.Shoes;
import utils.UserLogin;

/**
 * Created by Hodei Eceiza
 * Date: 2/14/2021
 * Time: 21:47
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesDescription {
    public ImageView shoesImage;
    public Label brandL;
    public Label sizeL;
    public Label colorL;
    public Label categoryL;
    public Label onStockL;
    public Label priceL;
    public Button addToCartB;
    public Spinner<Integer> quantityS;

    public void initialize(){
    }
    public void setData(Shoes shoesData, ObservableList<Shoes> shoppingCart,Label totalPriceL){

        if(!UserLogin.getIsLogged())
            addToCartB.setVisible(false);


        brandL.setText(shoesData.getBrand());
        colorL.setText(shoesData.getColor());
        priceL.setText(shoesData.getPrice()+"");
        sizeL.setText(shoesData.getSize()+"");
        onStockL.setText((shoesData.getQuantity()>0)?"ON STOCK":"NO AVAILABLE");

        addToCartB.setOnMouseClicked(e-> {

            shoesData.setQuantity(quantityS.getValue());
            shoppingCart.add(shoesData);
            //update Label
            totalPriceL.setText(shoppingCart.stream().map(Shoes::getPrice).reduce(0,(a,b)->a+b).toString());
        });

        quantityS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,shoesData.getQuantity()));


    }
}
