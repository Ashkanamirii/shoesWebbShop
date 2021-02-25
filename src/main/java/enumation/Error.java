package enumation;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-25
 * Time:  00:59
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public enum Error {
    DATABASE(0, "A database error has occurred."),
    DUPLICATE_USER(5007, "This user already exists.");

    private final int code;
    private final String description;

    private Error(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
