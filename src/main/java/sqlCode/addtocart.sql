-- renewed addtocart, will write it more beautiful another day. just inserts in order line item with the given status.
-- to update the shoes stock we use update_stock_on_status (look on triggers_views_och storeProcedure.sql)
DELIMITER
//
create procedure AddToCart(
    IN customerId int,
    IN orderId int,
    IN shoesId int,
    IN ordered_quantity int,
    IN _status int)

BEGIN
    DECLARE
getOrderID int;

-- första click
if
orderId=-1 then set orderId=null;
end if;
    if (_status = 2 and orderId is null)
    then -- PAYING måste insertera i order line item
        insert into orders(FK_customer_id, order_date) VALUES (customerId, current_date());
        set
getOrderID = last_insert_id();
insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
VALUES (shoesId, getOrderID, ordered_quantity, _status);
-- update shoes set quantity = quantity - ordered_quantity where id = shoesId; -- (Det kan fixa med triggers) eller kanske det behöver inte
else if (_status = 2)
 then insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
            VALUES (shoesId, orderId, ordered_quantity, _status);

else
        if (_status in (3, 4))
        then -- lägga till shoes Q
            insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
            VALUES (shoesId, orderId, ordered_quantity, _status);
-- update shoes set quantity = quantity + ordered_quantity where id = shoesId; (Det kan fixa med triggers) eller kanske det behöver inte

else
            if (_status = 1)
            then -- CONFIRMED måste insertera i order line item

                insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                VALUES (shoesId, orderId, ordered_quantity, _status);
              --  update shoes set quantity = quantity - ordered_quantity where id = shoesId;

else
                if (_status = 5)
                then -- RETURNED insertera i order line item och order och lägga till Q  shoes
                    insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                    VALUES (shoesId, orderId, ordered_quantity, _status);
                --    update shoes set quantity = quantity + ordered_quantity where id = shoesId;

END IF;
END IF;
END IF;
END IF;
END IF;
end
//
DELIMITER ;