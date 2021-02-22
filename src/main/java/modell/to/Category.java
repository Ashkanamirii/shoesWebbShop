package modell.to;

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

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String shoesCategories, String categorieIds) {
        this.shoesCategories = shoesCategories;
        this.categorieIds = categorieIds;
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
}
