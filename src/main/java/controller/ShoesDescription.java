package controller;

import enumation.Error;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.AnchorPane;
import model.bl.SurveysBLImpl;
import model.to.Shoes;
import org.controlsfx.control.Rating;
import model.to.Comments;
import utils.UserLogin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;


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
    public Rating averageRating;
    public AnchorPane shoesDescrptionPane;
    public TableView <Comments>commentsTable;
    public TableColumn <Comments,String>userNameC;
    public TableColumn <Comments,String>userCommentC;
    public Label quantityL;
    private Shoes selectedShoes;
    private SurveysBLImpl surveysManager=new SurveysBLImpl();
    private double ratingValue;
    private ObservableList<Comments> commentsList;

    public void initialize() {
    }

    public void setData(Shoes shoesData, ObservableList<Shoes> shoppingCart, Label totalPriceL) {
        getImagePath(shoesData, shoesImage);
        if (!UserLogin.getIsLogged()) {
            addToCartB.setVisible(false);
            quantityS.setVisible(false);
            quantityL.setVisible(false);
        }

        try {
            ratingValue = surveysManager.getAvgForOneShoes(shoesData.getId());
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
            System.out.println(Error.DATABASE);
            System.out.println(Error.CONNECTION);
        }
        averageRating.setRating(ratingValue);
        averageRating.setPartialRating(true);
        averageRating.setOnMouseClicked(e->averageRating.setRating(ratingValue));
        brandL.setText(shoesData.getBrand().getName());
        colorL.setText(shoesData.getColor());
        categoryL.setText(shoesData.getCategoriesP());
        priceL.setText(shoesData.getPrice() + "");
        sizeL.setText(shoesData.getSize() + "");
        onStockL.setText((shoesData.getQuantity() > 0) ? "ON STOCK" : "NO AVAILABLE");

        addToCartB.setOnMouseClicked(e -> {
            selectedShoes=new Shoes(shoesData.getId(), shoesData.getSize(), shoesData.getShoes_number(),
                    shoesData.getBrand(),shoesData.getCategories(),
                    shoesData.getColor(), shoesData.getPrice(), quantityS.getValue());

            if (shoppingCart.stream().anyMatch(s -> s.getId() == shoesData.getId())) {
                int index = shoppingCart.indexOf(shoppingCart.stream()
                                                .filter(s -> s.getId() == shoesData.getId())
                                                .findFirst().get());
                selectedShoes.setQuantity(shoppingCart.get(index).getQuantity() + quantityS.getValue());
                shoppingCart.set(index,selectedShoes);
            } else
                shoppingCart.add(selectedShoes);
            totalPriceL.setText(shoppingCart.stream().
                    map(s -> s.getPrice() * s.getQuantity()).reduce(0.0, (f,s)->f+s).toString());
        });

        quantityS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, shoesData.getQuantity()));
        try {
            commentsList= FXCollections.observableArrayList(
                    surveysManager.getCommentsByShoesId(shoesData.getShoes_number()));
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            System.out.println(Error.DATABASE);
            System.out.println(Error.CONNECTION);
        }

        userNameC.setCellValueFactory(new PropertyValueFactory("userName"));
        userCommentC.setCellValueFactory(new PropertyValueFactory("userComment"));
        commentsTable.setItems(commentsList);
    }
    // TODO Fixe CLEAN CODE
    public static void getImagePath(Shoes shoesData, ImageView shoesImage) {
        File file = new File("src/main/resources/img/reebok.jpg");
        File file1 = new File("src/main/resources/img/adibas.png");
        String imagepath = null;
        String imagepath1 = null;
        try {
             imagepath = file.toURI().toURL().toString();
             imagepath1 = file1.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(Error.MALFORMEDURLEXCEPTION);
        }
        switch(shoesData.getBrand().getName()){
            case "Adidas"-> shoesImage.setImage(new Image(imagepath1));
            case "Reebok"-> shoesImage.setImage(new Image(imagepath));
        }
    }
}
