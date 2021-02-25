package model.to;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-19
 * Time:  10:19
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class NoStock {
    private int id;
    private Shoes shoesId;
    private String endDate;

    public NoStock(int id, Shoes shoesId, String endDate) {
        this.id = id;
        this.shoesId = shoesId;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shoes getShoesId() {
        return shoesId;
    }

    public void setShoesId(Shoes shoesId) {
        this.shoesId = shoesId;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
