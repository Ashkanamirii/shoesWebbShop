package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modell.bl.SurveysBLImpl;
import modell.to.Shoes;
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
    private SurveysBLImpl surveys=new SurveysBLImpl();
    public void initialize(){

        try {
            surveys.getAvgForOneShoes(shoesId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void setData(int shoesId){
        this.shoesId=shoesId;
    }

}
