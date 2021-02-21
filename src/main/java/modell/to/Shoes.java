package modell.to;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:26
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Shoes {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty size;
    private SimpleIntegerProperty shoes_number;
    private SimpleStringProperty brand;
    private int brandId;//TODO: tar FK_brand_id
    private Brand brand1;
    private SimpleStringProperty color;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public Shoes(int id, int size, int shoes_number, String brand, String color, double price, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.size = new SimpleIntegerProperty(size);
        this.shoes_number = new SimpleIntegerProperty(shoes_number);
        this.brand = new SimpleStringProperty(brand);
        this.color = new SimpleStringProperty(color);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);

    }
    public Shoes(int id, int size, int shoes_number, Brand brand, String color, int price, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.size = new SimpleIntegerProperty(size);
        this.shoes_number = new SimpleIntegerProperty(shoes_number);
        this.brand1 = brand;
        this.color = new SimpleStringProperty(color);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);

    }

    public Shoes() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public int getSize() {
        return size.get();
    }

    public SimpleIntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size = new SimpleIntegerProperty(size);
    }

    public int getShoes_number() {
        return shoes_number.get();
    }

    public SimpleIntegerProperty shoes_numberProperty() {
        return shoes_number;
    }

    public void setShoes_number(int shoes_number) {
        this.shoes_number = new SimpleIntegerProperty(shoes_number);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = new SimpleStringProperty(brand);
    }

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color = new SimpleStringProperty(color);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }

}
