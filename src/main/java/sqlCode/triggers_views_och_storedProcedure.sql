-- updates the stock based on status.
delimiter //
create trigger on_status_update
    after insert
    on order_line_item
    for each row
begin
case
    when new.status='RETURNED' or new.status='CANCEL'
		then update shoes set quantity=shoes.quantity+new.quantity where shoes.id=new.FK_shoes_id;
when new.status='PAYING'
		then update shoes set quantity=shoes.quantity-new.quantity where shoes.id=new.FK_shoes_id;
else update shoes set quantity=shoes.quantity where shoes.id=new.FK_shoes_id;
end case;
-- end if;
end//
delimiter ;


drop trigger if exists update_no_stock;
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


drop function if exists  getCategoryNameById;

DELIMITER //
create function getCategoryNameById (categoryId int)
    returns varchar(50)
    reads sql data
begin
declare categoryName varchar(50);
select name into categoryName from category where id=categoryId;
return categoryName;
end//
 DELIMITER ;

DELIMITER //
create function getCategoryIdsByShoesId(shoesId int)
    returns varchar(255)
    reads sql data
begin
declare categoryIds varchar(50);
with categories as(select FK_shoes_id,group_concat(c.name separator ', ') as category,group_concat(c.id separator ', ') as _categoryIds
    from shoes_category
    join category c on c.id=FK_category_id
group by FK_shoes_id)
select _categoryIds into categoryIds
from shoes
         join brand br on br.id=shoes.FK_brand_id
         left join categories cs on cs.FK_shoes_id=shoes.id
where shoes.id=shoesId;
return categoryIds;
end//
DELIMITER ;




DELIMITER //
create procedure getOrderIDForInvoice(OUT orderId int, IN customerId int)
BEGIN
    select max(id)  into orderId from orders o where customerId= o.FK_customer_id;
end//
DELIMITER ;
