DELIMITER //
create procedure AddToCart(
    IN customerId int,
    IN orderId int,
    IN shoesId int,
    IN ordered_quantity int,
    IN _status int)

BEGIN
    DECLARE getOrderID int;
-- första click

    if (_status = 2 and orderId is null)
    then -- PAYING sänka bara shoes Q och vänta 5 min
        insert into orders(FK_customer_id, order_date) VALUES (1, current_date());
        set getOrderID = last_insert_id();
        insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
        VALUES (shoesId, getOrderID, ordered_quantity, _status);

-- update shoes set quantity = quantity - ordered_quantity where id = shoesId;  (Det kan fixa med triggers) eller kanske det behöver inte

    else
        if (_status in (3, 4))
        then -- lägga till shoes Q
            insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
            VALUES (shoesId, orderId, ordered_quantity, _status);
-- update shoes set quantity = quantity + ordered_quantity where id = shoesId; (Det kan fixa med triggers) eller kanske det behöver inte

        else
            if (_status = 1)
            then -- CONFIRMED måste insertera i order line item och order och sänka Q from shoes

                insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                VALUES (shoesId, orderId, ordered_quantity, _status);
                update shoes set quantity = quantity - ordered_quantity where id = shoesId;

            else
                if (_status = 5)
                then -- RETURNED insertera i order line item och order och lägga till Q  shoes
                    insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                    VALUES (shoesId, orderId, ordered_quantity, _status);
                    update shoes set quantity = quantity + ordered_quantity where id = shoesId;

                END IF;
            END IF;
        END IF;
    END IF;

end//
DELIMITER ;


-- första tryck på add to cart
call AddToCart(1, null, 8, 14, 2);

-- cancle eller auto cancle
call AddToCart(null, null, 4, 8, 3);
call AddToCart(null, null, 5, 1, 4);

-- CONFIRMED
call AddToCart(1, 20, 8, 14, 1);
call AddToCart(1, -1, 6, 5, 1);

-- RETURNED
call AddToCart(1, 19, 6, 3, 5);











