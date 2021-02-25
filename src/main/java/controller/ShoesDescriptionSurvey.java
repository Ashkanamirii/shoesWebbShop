package controller;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modell.bl.ShoesManagerImpl;
import modell.bl.SurveysBLImpl;
import modell.to.Shoes;
import org.controlsfx.control.Rating;
import utils.UserLogin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hodei Eceiza
 * Date: 2/24/2021
 * Time: 13:34
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesDescriptionSurvey {
    public AnchorPane shoesDescrption_surveyPane;
    public ImageView shoesImage;
    public Label brandL;
    public Label shoesNrL;
    public Label colorL;
    public Label categoryL;
    public Button submitB;
    public Label priceL;
    private final ShoesManagerImpl shoesManager=new ShoesManagerImpl();
    public Rating ratingStars;
    private List<Shoes> shoesList;
    private int ratingValue;
    private SurveysBLImpl sendSurvey=new SurveysBLImpl();
    public TextArea commentTextArea;
    public void initialize(){
        try {
            shoesList=shoesManager.getAllShoes();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        ratingStars.setRating(2.5);
        ratingStars.setPartialRating(true);
    }
    public void setData(int shoesId){
        Shoes shoesData=shoesList.get(shoesId-1);
        File file = new File("src/main/resources/img/reebok.jpg");
        File file1 = new File("src/main/resources/img/adibas.png");
        String imagepath = null;
        String imagepath1 = null;
        try {
            imagepath = file.toURI().toURL().toString();
            imagepath1 = file1.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch(shoesData.getBrand().getName()){
            case "Adidas"->shoesImage.setImage(new Image(imagepath1));
            case "Reebok"->shoesImage.setImage(new Image(imagepath));
        }
        


            ratingStars.setOnMouseClicked(e->ratingValue=(int)ratingStars.getRating());
            submitB.setOnAction(e->{
                //Alert dialog
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("THANK YOU");
                a.setContentText("Thank you for the rating and comment");
                submitB.setDisable(true);
                a.showAndWait();
                try {
                    sendSurvey.setSurveys(UserLogin.getCustomer().getId(),shoesId,ratingValue,commentTextArea.getText());
                } catch (SQLException | IOException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            });
       

        //the labels and descriptions

        brandL.setText(shoesData.getBrand().getName());
        colorL.setText(shoesData.getColor());
        categoryL.setText(shoesData.getCategoriesP());
        priceL.setText(shoesData.getPrice() + "");
        shoesNrL.setText(shoesData.getShoes_number() + "");



    }
}
