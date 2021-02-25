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
    CONNECTION(2, "CONNECTION ERROR"),
    DUPLICATE_USER(5007, "This user already exists."),
    MALFORMEDURLEXCEPTION(1010, "URL EXCEPTION"),
    SURVEY_SUBMIT(3, ""),
    RETURNED_FAILED(1, "You can't buy more shoes with this order\nBut you can always go back to the shop and buy more :)");

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
