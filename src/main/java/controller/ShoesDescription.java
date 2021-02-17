package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import modell.Shoes;
import utils.UserLogin;

import java.util.stream.Collector;

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

    public void initialize() {
    }

    public void setData(Shoes shoesData, ObservableList<Shoes> shoppingCart, Label totalPriceL) {
        System.out.println(shoesData.getQuantity());
        if (!UserLogin.getIsLogged())
            addToCartB.setVisible(false);


        brandL.setText(shoesData.getBrand());
        colorL.setText(shoesData.getColor());
        priceL.setText(shoesData.getPrice() + "");
        sizeL.setText(shoesData.getSize() + "");
        onStockL.setText((shoesData.getQuantity() > 0) ? "ON STOCK" : "NO AVAILABLE");

        addToCartB.setOnMouseClicked(e -> {
//TODO:rewrite this code to be more clear
            if (shoppingCart.stream().anyMatch(s -> s.getId() == shoesData.getId())) {
                int index = shoppingCart.indexOf(shoppingCart.stream().filter(s -> s.getId() ==
                        shoesData.getId()).findFirst().get());

                shoppingCart.set(index, new Shoes(shoesData.getId(), shoesData.getSize(), shoesData.getShoes_number(),
                        shoesData.getBrand(), shoesData.getColor(), shoesData.getPrice(),
                        shoppingCart.get(index).getQuantity() + quantityS.getValue()));

            } else
                shoppingCart.add(new Shoes(shoesData.getId(), shoesData.getSize(), shoesData.getShoes_number(),
                        shoesData.getBrand(), shoesData.getColor(), shoesData.getPrice(), quantityS.getValue()));

            //update totalPrice
            totalPriceL.setText(shoppingCart.stream().
                    map(s -> s.getPrice() * s.getQuantity()).reduce(0, Integer::sum).toString());
        });

        quantityS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, shoesData.getQuantity()));


    }
}
