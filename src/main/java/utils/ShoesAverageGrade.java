package utils;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-23
 * Time:  15:05
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class ShoesAverageGrade {

    private SimpleStringProperty shoesName;
    private SimpleIntegerProperty shoesNumber;
    private SimpleLongProperty numberOfRating;
    private SimpleDoubleProperty averageRate;
    private SimpleStringProperty rate;
    private SimpleListProperty<String> ListOfComment;

    public ShoesAverageGrade(String shoesName,
                             int shoesNumber, Long numberOfRating, double averageRate, String rate ,
                             ObservableList<String> ListOfComment) {
        this.shoesName = new SimpleStringProperty (shoesName);
        this.shoesNumber = new SimpleIntegerProperty(shoesNumber);
        this.numberOfRating = new SimpleLongProperty(numberOfRating);
        this.averageRate = new SimpleDoubleProperty(averageRate);
        this.rate = new SimpleStringProperty (rate);
        this.ListOfComment = new SimpleListProperty<>(ListOfComment);
    }

    public String getShoesName() {
        return shoesName.get();
    }

    public SimpleStringProperty shoesNameProperty() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName.set(shoesName);
    }

    public int getShoesNumber() {
        return shoesNumber.get();
    }

    public SimpleIntegerProperty shoesNumberProperty() {
        return shoesNumber;
    }

    public void setShoesNumber(int shoesNumber) {
        this.shoesNumber.set(shoesNumber);
    }

    public long getNumberOfRating() {
        return numberOfRating.get();
    }

    public SimpleLongProperty numberOfRatingProperty() {
        return numberOfRating;
    }

    public void setNumberOfRating(long numberOfRating) {
        this.numberOfRating.set(numberOfRating);
    }

    public double getAverageRate() {
        return averageRate.get();
    }

    public SimpleDoubleProperty averageRateProperty() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate.set(averageRate);
    }

    public String getRate() {
        return rate.get();
    }

    public SimpleStringProperty rateProperty() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate.set(rate);
    }

    public ObservableList<String> getListOfComment() {
        return ListOfComment.get();
    }

    public SimpleListProperty<String> listOfCommentProperty() {
        return ListOfComment;
    }

    public void setListOfComment(ObservableList<String> listOfComment) {
        this.ListOfComment.set(listOfComment);
    }
}
