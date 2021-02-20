-- updates the stock based on status.
delimiter //
create trigger update_stock_on_status

    after insert
    on order_line_item
    for each row
begin
    case
        when new.status = 'RETURNED' or new.status = 'CANCEL' or new.status = 'AUTO_CANCEL'
            then update shoes set quantity = shoes.quantity + new.quantity where shoes.id = new.FK_shoes_id;
        when new.status = 'PAYING'
            then update shoes set quantity = shoes.quantity - new.quantity where shoes.id = new.FK_shoes_id;
        end case;
end//
delimiter ;

-- update table NoStock
delimiter //
create trigger update_no_stock
    after update
    on shoes
    for each row
    if (select quantity
        from shoes
        where id = new.id) <= 0 then
        insert into no_stock(FK_shoes_id) values (new.id);
    end if//
delimiter ;

-- DOWN HERE EXPERIMENTS AND OLD DRAFTS

-- view for color and size.alter
/*
create view colors AS
select distinct color
from shoes;

create view sizes AS
select distinct size
from shoes;
*/
-- need to insert an order id


-- SP to get order id when we create a new one (should be simplified, no need of null.) and maybe transform to a function.
DELIMITER //
create procedure getNewOrderId(INOUT orderId int, IN customerId int)
BEGIN
    if orderId = -1 then
        set orderId = null;
    end if;
    if orderId is null then
        insert into orders (FK_customer_id, order_date) values (customerId, curdate());
        set orderId := last_insert_id();
    end if;
end//
DELIMITER ;

drop procedure getNewOrderId;



