package model.to;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:27
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Orders {
    private int id;
    private Customer customerId;
    private String orderDate;

    public Orders() {
    }

    public Orders(int id, Customer customerId, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
