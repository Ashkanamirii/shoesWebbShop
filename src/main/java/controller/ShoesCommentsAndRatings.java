package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.bl.SurveysBLImpl;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Hodei Eceiza
 * Date: 2/24/2021
 * Time: 09:00
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesCommentsAndRatings {
    public TableView commentsTable;
    public TableColumn userNameC;
    public TableColumn userCommentC;
    public Rating ratingStars;
    public Label shoesNameL;
    public Label shoesNumberL;
    public Label averageRateL;
    public Label rateNameL;
    private int shoesId;
    private double ratingValue;
    private SurveysBLImpl surveys=new SurveysBLImpl();
    public void initialize(){
//set stars rating
        try {
            ratingValue=surveys.getAvgForOneShoes(shoesId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ratingStars.setRating(ratingValue);
        ratingStars.setOnMouseClicked(e->ratingStars.setRating(ratingValue));


    }
    public void setData(int shoesId){
        this.shoesId=shoesId;
    }

}
