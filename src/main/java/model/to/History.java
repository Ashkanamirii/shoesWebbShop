package model.to;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hodei Eceiza
 * Date: 2/23/2021
 * Time: 12:38
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class History {
    private SimpleIntegerProperty orderId;
    private SimpleStringProperty orderDate;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty price;
    private SimpleStringProperty shoesColor;
    private SimpleIntegerProperty shoesNumber;
    private SimpleStringProperty shoesBrandName;
    private SimpleStringProperty status;
    private SimpleDoubleProperty total_price;
    private SimpleIntegerProperty shoesId;

    public History(int orderId, String orderDate, int quantity, double price,
                   String shoesColor, int shoesNumber, String shoesBrandName, String status, double total_price,int shoesId) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.orderDate = new SimpleStringProperty (orderDate);
        this.shoesBrandName = new SimpleStringProperty (shoesBrandName);
        this.shoesColor = new SimpleStringProperty (shoesColor);
        this.shoesNumber = new SimpleIntegerProperty(shoesNumber);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.status = new SimpleStringProperty (status);
        this.total_price = new SimpleDoubleProperty(total_price);
        this.shoesId = new SimpleIntegerProperty(shoesId);
    }

    public int getShoesId() {
        return shoesId.get();
    }

    public SimpleIntegerProperty shoesIdProperty() {
        return shoesId;
    }

    public void setShoesId(int shoesId) {
        this.shoesId.set(shoesId);
    }

    public int getOrderId() {
        return orderId.get();
    }

    public SimpleIntegerProperty orderIdProperty() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId.set(orderId);
    }

    public String getOrderDate() {
        return orderDate.get();
    }

    public SimpleStringProperty orderDateProperty() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate.set(orderDate);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getShoesColor() {
        return shoesColor.get();
    }

    public SimpleStringProperty shoesColorProperty() {
        return shoesColor;
    }

    public void setShoesColor(String shoesColor) {
        this.shoesColor.set(shoesColor);
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

    public String getShoesBrandName() {
        return shoesBrandName.get();
    }

    public SimpleStringProperty shoesBrandNameProperty() {
        return shoesBrandName;
    }

    public void setShoesBrandName(String shoesBrandName) {
        this.shoesBrandName.set(shoesBrandName);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public double getTotal_price() {
        return total_price.get();
    }

    public SimpleDoubleProperty total_priceProperty() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price.set(total_price);
    }
}
