
-- *************************************************************
-- Skapa en stored procedure ”Rate” som lägger till ett betyg och en kommentar på en specifik produkt
-- för en specifik kund
-- *************************************************************
DROP procedure IF EXISTS rate;

DELIMITER $$
CREATE PROCEDURE rate (
    IN customerId INT, IN shoesId INT,
    IN rate INT, IN surveysComment varchar(255)
)
BEGIN
    INSERT INTO surveys(comment,grade, FK_shoes_id, FK_customer_id, issue_date)
    VALUES
    (surveysComment, rate, shoesId, customerId , current_time);
END $$

DELIMITER ;

call rate(1,9,3,'its so so');
call rate(1,3,1,'AWFUL');
call rate(4,3,5,'BEST IN THE WORLD');
call rate(6,3,1,'I hate these shoes');
call rate(1,5,5,'BEST IN THE WORLD');

select *  from surveys;
-- *************************************************************

-- *************************************************************

DELIMITER $$
create procedure customerHistory(
in custID INT
)
BEGIN
    select o.id as ORDER_ID , o.order_date as DATE, oli.quantity ,sh.price, sh.color ,
           sh.shoes_number , b.name, oli.status , sh.price * oli.quantity as total_price,sh.id as shoes_ID
    from customer c
        join orders o on o.FK_customer_id = c.id
        join order_line_item oli on oli.FK_order_id = o.id
        join shoes sh on sh.id = oli.FK_shoes_id
        join brand b on b.id = sh.FK_brand_id
    where c.id = custID and oli.status = 1 and oli.quantity > 0
    order by DATE;
END $$

DELIMITER ;

-- *************************************************************

-- *************************************************************

DELIMITER $$
create procedure invoice(
    in ordID INT
)
BEGIN
    select o.id as ORDER_ID , o.order_date as DATE, b.name,  sh.color ,
           sh.shoes_number , oli.quantity  , sh.price ,
           oli.quantity * sh.price as total_price ,sh.id as shoes_id from customer c
           join
               orders o on o.FK_customer_id = c.id
           join
               order_line_item oli on oli.FK_order_id = o.id
           join
               shoes sh on sh.id = oli.FK_shoes_id
           join
               brand b on b.id = sh.FK_brand_id
    where o.id = ordID and oli.status in(1)
    order by DATE;
END $$

DELIMITER ;

-- *************************************************************

-- *************************************************************
DELIMITER //
create procedure getOrderIDForInvoice(OUT orderId int, IN customerId int)
BEGIN
    select max(id)  into orderId from orders o where customerId= o.FK_customer_id;
end//
DELIMITER ;

-- *************************************************************

-- *************************************************************
delimiter //
create procedure help_auto_cancel()
BEGIN
    Declare ss int DEFAULT 0;
    select count(status) into ss from order_line_item where status = 2;
    if ss > 0
    then
        update LOW_PRIORITY IGNORE order_line_item  set status = 4
        where status = 2;
    end if;
END //
delimiter ;
-- *************************************************************

-- *************************************************************