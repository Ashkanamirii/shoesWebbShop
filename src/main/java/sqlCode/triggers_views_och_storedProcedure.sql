
-- update shoes quantity by order quantity
create trigger update_stock
    after insert
    on order_line_item
    for each row
    update shoes
    set shoes.quantity = shoes.quantity - NEW.quantity
    where shoes.id = NEW.FK_shoes_id;

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
create procedure getNewOrderId(INOUT orderId int ,IN customerId int)
BEGIN
if orderId=-1 then set orderId=null;
end if;
if orderId is null then insert into orders (FK_customer_id,order_date) values (customerId,curdate());
set orderId := last_insert_id();
end if;
end//
DELIMITER ;

-- drop procedure getNewOrderId;


-- Add to cart combined with getNewOrderId can create a new order. it can update an order, and can change the status if the product is returned
DELIMITER //
create procedure AddToCart (IN customerId int, IN orderId int, IN shoesId int, IN ordered_quantity int, IN returned boolean)
BEGIN

    if returned is true
    then
update order_line_item set status = 5 where FK_order_id=orderId AND FK_shoes_id=shoesId;
else
		if (select FK_shoes_id from order_line_item where FK_order_id=orderId) = shoesId then
update order_line_item set FK_shoes_id=shoesId, quantity=ordered_quantity where FK_order_id=orderId AND FK_shoes_id=shoesId;
else
		insert into order_line_item (FK_order_id,FK_shoes_id,quantity) values (orderId,shoesId,ordered_quantity);
end if;
end if;
end//
DELIMITER ;

-- trigger for status can transform to case, if want to trigger something else with the different status
delimiter //
create trigger on_status_update
    after update on order_line_item
    for each row
begin
    if new.status<>old.status and new.status= 'RETURNED' then update shoes set quantity=shoes.quantity+new.quantity where shoes.id=new.FK_shoes_id;
end if;
end//
delimiter ;
