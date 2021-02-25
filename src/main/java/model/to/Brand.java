package model.to;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-19
 * Time:  10:18
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Brand {
    private int id;
    private String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
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
}
