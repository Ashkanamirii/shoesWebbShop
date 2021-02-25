package model.to;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-19
 * Time:  10:20
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class OrderLineItem {
    private Shoes shoesId ;
    private Orders OrdersId ;
    private int quantity;
    private String status;

    public OrderLineItem() {
    }

    public OrderLineItem(Shoes shoesId, Orders ordersId, int quantity, String status) {
        this.shoesId = shoesId;
        OrdersId = ordersId;
        this.quantity = quantity;
        this.status = status;
    }

    public Shoes getShoesId() {
        return shoesId;
    }

    public void setShoesId(Shoes shoesId) {
        this.shoesId = shoesId;
    }

    public Orders getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(Orders ordersId) {
        OrdersId = ordersId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
