-- *************************************************************
-- renewed addtocart, will write it more beautiful another day.
-- just inserts in order line item with the given status.
-- to update the shoes stock we use update_stock_on_status (look on triggers_views_och storeProcedure.sql)
-- *************************************************************
drop procedure if exists AddToCart;
DELIMITER //
create procedure AddToCart(
    IN customerId int,
    IN orderId int,
    IN shoesId int,
    IN ordered_quantity int,
    IN _status int,
    OUT created_order_id int)

BEGIN
    DECLARE getOrderID int;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            ROLLBACK;
            -- select ('SQLEXCEPTION occurred, rollback done');
            RESIGNAL SET MESSAGE_TEXT = 'SQLEXCEPTION occurred, rollback done';
        END;

    DECLARE EXIT HANDLER FOR SQLWARNING
        BEGIN
            ROLLBACK;
            -- select ('SQLWARNING occurred, rollback done');
            RESIGNAL SET MESSAGE_TEXT = 'SQLWARNING occurred, rollback done';
        END;

    DECLARE EXIT HANDLER FOR 1062
        begin
            ROLLBACK;
            -- select ('unique constraint broken, rollback done') as error;
            RESIGNAL SET MESSAGE_TEXT = 'unique constraint broken, rollback done';
        END;

-- första click
    start transaction;
    if
        orderId = -1 then
        set orderId = null;
    end if;

    if (_status = 2 and orderId is null)
    then -- PAYING måste insertera i order line item
        insert into orders(FK_customer_id, order_date) VALUES (customerId, current_date());
        set
            getOrderID = last_insert_id();
        set created_order_id = getOrderID;
        select created_order_id;
        insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
        VALUES (shoesId, getOrderID, ordered_quantity, _status);
    else
        if (_status = 2)
        then
            insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
            VALUES (shoesId, orderId, ordered_quantity, _status);
        else
            if (_status = 3)
            then
                insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                VALUES (shoesId, orderId, ordered_quantity, _status);
            else
                if (_status = 1)
                then -- CONFIRMED måste insertera i order line item
                    update order_line_item set status = 1 where FK_order_id = orderId;
                else
                    if (_status = 5)
                    then -- RETURNED insertera i order line item och order och lägga till Q  shoes
                        update order_line_item set quantity = quantity - ordered_quantity
                        where shoesId = FK_shoes_id and orderId = FK_order_id and status = 1;

                        insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
                        VALUES (shoesId, orderId, ordered_quantity, _status)
                        on duplicate key update quantity= ordered_quantity;

                    END IF;
                END IF;
            END IF;
        END IF;
    END IF;
    -- select ('') as error;
    commit;
end//
DELIMITER ;
-- *************************************************************

-- *************************************************************