package utils;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-24
 * Time:  10:09
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Comment {
    SimpleStringProperty customerName;
    SimpleStringProperty comment;

    public Comment(String customerName, String comment) {
        this.customerName = new SimpleStringProperty(customerName);
        this.comment = new SimpleStringProperty(comment);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getComment() {
        return comment.get();
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }
}
