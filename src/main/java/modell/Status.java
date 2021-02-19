package modell;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-19
 * Time:  19:40
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Status {
    private String enumString;
    private int enumInt;

    public Status(String enumString, int enumInt) {
        this.enumString = enumString;
        this.enumInt = enumInt;
    }

    public String getEnumString() {
        return enumString;
    }

    public void setEnumString(String enumString) {
        this.enumString = enumString;
    }

    public int getEnumInt() {
        return enumInt;
    }

    public void setEnumInt(int enumInt) {
        this.enumInt = enumInt;
    }
}
