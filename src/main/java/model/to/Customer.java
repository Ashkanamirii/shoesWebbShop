package model.to;


/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:25
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Customer {
    private String name;
    private int id;
    private String phoneNumber;
    private String address;
    private String country;
    private String email;
    private String pswd;

    public Customer() {
    }

    public Customer(int id, String name, String phoneNumber, String address, String country, String email, String pswd) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.email = email;
        this.pswd = pswd;
    }

    public Customer(String name, String phoneNumber, String address, String country, String email, String pswd) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.email = email;
        this.pswd = pswd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}