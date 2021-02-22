package modell.to;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-19
 * Time:  10:19
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Category {
    private int id;
    private String name;
    private String shoesCategories;
    private String categorieIds;
    private List<Shoes> shoesList;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String shoesCategories, String categorieIds) {
        this.shoesCategories = shoesCategories;
        this.categorieIds = categorieIds;
    }

    public Category(int id, String name, List<Shoes> shoesList) {
        this.id = id;
        this.name = name;
        this.shoesList = shoesList;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShoesCategories() {
        return shoesCategories;
    }

    public void setShoesCategories(String shoesCategories) {
        this.shoesCategories = shoesCategories;
    }

    public String getCategorieIds() {
        return categorieIds;
    }

    public void setCategorieIds(String categorieIds) {
        this.categorieIds = categorieIds;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }
}
