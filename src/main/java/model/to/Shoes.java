package model.to;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.*;
import java.util.stream.Collectors;


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
    private SimpleStringProperty brandP;
    private SimpleStringProperty categoriesP;
    private Brand brand;
    private List<Category> categories;
    private SimpleStringProperty color;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public Shoes(int id, int size, int shoes_number, Brand brand,
                 List<Category>categories,String color, double price, int quantity) {
        this.id = new SimpleIntegerProperty(id);
        this.size = new SimpleIntegerProperty(size);
        this.shoes_number = new SimpleIntegerProperty(shoes_number);
        this.brandP = new SimpleStringProperty(brand.getName());
        this.categories=categories;
        if (categories !=null && categories.size()>0) {
            this.categoriesP = new SimpleStringProperty(categories.stream().
                    map(Category::getName).collect(Collectors.joining(", ")));
        }

        this.brand=brand;
        this.color = new SimpleStringProperty(color);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);

    }

    public Shoes() {

    }

    public String getCategoriesP() {


        return categoriesP.get();
    }

    public SimpleStringProperty categoriesPProperty() {
        return categoriesP;
    }

    public void setCategoriesP(String categoriesP) {
        this.categoriesP.set(categoriesP);
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void setCategoriesNameList(List<Category> categories) {
        this.categoriesP = new SimpleStringProperty(categories.stream().map(Category::getName).collect(Collectors.joining(", ")));
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

    public String getBrandP() {
        return brandP.get();
    }



    public SimpleStringProperty brandProperty() {
        return brandP;
    }

    public void setBrandP(String brand) {
        this.brandP = new SimpleStringProperty(brand);
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
