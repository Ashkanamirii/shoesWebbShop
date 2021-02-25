package controller;

import enumation.Error;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.bl.ShoesManagerImpl;
import model.bl.SurveysBLImpl;
import model.to.Shoes;
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
    public Rating ratingStars;
    public TextArea commentTextArea;
    private final ShoesManagerImpl shoesManager=new ShoesManagerImpl();
    private List<Shoes> shoesList;
    private int ratingValue;
    private SurveysBLImpl sendSurvey=new SurveysBLImpl();
    public void initialize(){
        try {
            shoesList=shoesManager.getAllShoes();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        ratingStars.setRating(3);
    }
    public void setData(int shoesId){
        Shoes shoesData=shoesList.get(shoesId-1);
        ShoesDescription.getImagePath(shoesData, shoesImage);
        ratingStars.setOnMouseClicked(e->ratingValue=(int)ratingStars.getRating());
            submitB.setOnAction(e->{
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("THANK YOU");
                a.setContentText("Thank you for the rating and comment");
                submitB.setDisable(true);
                a.showAndWait();
                try {
                    sendSurvey.setSurveys(UserLogin.getCustomer().getId(),shoesId,ratingValue,commentTextArea.getText());
                } catch (SQLException | IOException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                    System.out.println(Error.DATABASE);
                    System.out.println(Error.CONNECTION);
                }
            });
        brandL.setText(shoesData.getBrand().getName());
        colorL.setText(shoesData.getColor());
        categoryL.setText(shoesData.getCategoriesP());
        priceL.setText(shoesData.getPrice() + "");
        shoesNrL.setText(shoesData.getShoes_number() + "");
    }
}
