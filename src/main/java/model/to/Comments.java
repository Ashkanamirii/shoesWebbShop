package model.to;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hodei Eceiza
 * Date: 2/24/2021
 * Time: 11:19
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Comments {
    private SimpleStringProperty userName;
    private SimpleStringProperty userComment;

  public Comments(String userName,String userComment){
      this.userName=new SimpleStringProperty(userName);
      this.userComment=new SimpleStringProperty(userComment);
  }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserComment() {
        return userComment.get();
    }

    public SimpleStringProperty userCommentProperty() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment.set(userComment);
    }
}
