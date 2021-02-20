package modell;

import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-02-11
 * Time:  09:27
 * Project: shoesWebbShop
 * Copyright: MIT
 */
public class Orders {
    private List<OrderLineItem> orderLineItems;


    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

}
