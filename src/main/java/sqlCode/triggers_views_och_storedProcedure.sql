-- testing stuff
use shoes_workshop;

-- update shoes quantity by order quantity
create trigger update_stock
    after insert
    on order_line_item
    for each row
    update shoes
    set shoes.quantity = shoes.quantity - NEW.quantity
    where shoes.id = NEW.FK_shoes_id;

-- create table NoStock
create table no_stock
(
    id       int not null auto_increment primary key,
    shoes_id int not null,
    end_date timestamp default current_timestamp on update current_timestamp
);

-- drop the table, just in case we have a non desired error
drop table no_stock;


-- update table NoStock
delimiter //
create trigger update_no_stock
    after update
    on shoes
    for each row
    if (select quantity
        from shoes
        where id = new.id) <= 0 then
        insert into no_stock(shoes_id) values (new.id);
    end if//
delimiter ;

-- drop the trigger, just in case we have a fatal error
drop trigger update_no_stock;



-- view for color and size.alter
create view colors AS
select distinct color
from shoes;

create view sizes AS
select distinct size
from shoes;

-- need to insert an order id

drop trigger update_stock;

select *
from order_line_item;

insert into order_line_item(FK_shoes_id, FK_order_id, quantity)
values (10, 9, 10);
select *
from shoes;

SET SQL_SAFE_UPDATES = 0;

/*
IN(order.id int, customer_id int, shoes_id
1 Om beställningen inte finns eller om vi skickar in null som beställningsid ska en ny beställning skapas och produkten läggas till i den.


2 Om beställningen redan finns ska produkten läggas till i beställningen.
.

3 Om beställningen finns och produkten redan finns i den ska vi lägga till ytterligare ett exemplar av produkten i beställningen.


4 För varje produkt som blir tillagd i en beställning ska lagerantalet av produkten minska. //DONE with trigger
5 Använd dig av transaktioner och felhantering //

*/
DELIMITER //
create procedure AddToCart(IN customerId int, IN orderId int, IN shoesId int, IN _quantity int)
BEGIN
    -- 1
    if orderId is null
    then
        insert into orders (FK_customer_id, order_date) values (customerId, curdate());
        -- 2 och 3?
    elseif orderId is not null
    then
        update orders set order_date=curdate() where id = orderId;
        if (select FK_shoes_id from order_line_item where FK_order_id = orderId) = shoesId
            -- delete function
            -- status return for
        then
            update order_line_item
            set FK_shoes_id = shoesId,
                quantity    = _quantity
            where FK_order_id = orderId
              AND FK_shoes_id = shoesId;
        else
            insert into order_line_item (FK_order_id, FK_shoes_id, quantity) values (orderId, shoesId, _quantity);
        end if;
    end if;
end//
DELIMITER ;

drop procedure AddToCart;

-- testin addtocart
-- test if order is null
call AddToCart(6, null, 3, 50);
-- test if order exists and has sent product (customer 5 changes order 15, changes quantity of shoes id 10 to 9)
call AddToCart(5, 15, 10, 9);
-- test if order exists but has no sent product (customer 5 changes order 15, adds shoes 1 with quantity 3)
call addToCart(5, 15, 1, 3);
select id, FK_customer_id
from orders
where id = 4;