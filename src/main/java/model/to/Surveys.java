package model.to;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  10:00
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Surveys {
    private int id;
    private String comment;
    private String grade;
    private String issueDate;
    private Shoes shoesId;
    private Customer customerId;

    public Surveys() {
    }

    public Surveys(int id, String comment, String grade, String issueDate, Shoes shoesId, Customer customerId) {
        this.id = id;
        this.comment = comment;
        this.grade = grade;
        this.issueDate = issueDate;
        this.shoesId = shoesId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Shoes getShoesId() {
        return shoesId;
    }

    public void setShoesId(Shoes shoesId) {
        this.shoesId = shoesId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }
}
