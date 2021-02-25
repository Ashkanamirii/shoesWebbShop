-- Det kör efter SP auto cancel
drop  trigger on_status_update_autoCancel;
delimiter //
create trigger on_status_update_autoCancel
    AFTER UPDATE
    ON order_line_item FOR EACH ROW
BEGIN
    if  OLD.status = 'PAYING' AND new.status = 'AUTO_CANCEL'
    then
        update LOW_PRIORITY IGNORE shoes sh set sh.quantity = sh.quantity + new.quantity
        where sh.id = new.FK_shoes_id;
    end if;
     if OLD.status = 1 AND  NEW.status = 5
         then
             update LOW_PRIORITY IGNORE shoes sh set sh.quantity = sh.quantity + new.quantity
             where sh.id = new.FK_shoes_id;
         end if;
end //
delimiter ;

-- *************************************************************

-- *************************************************************
# create trigger on_status_update_autoCancel
#     after update
#     on order_line_item
#     for each row
# BEGIN
#     if  OLD.status = 'PAYING' AND new.status = 'AUTO_CANCEL'
#     then
#         update LOW_PRIORITY IGNORE shoes sh set sh.quantity = sh.quantity + new.quantity
#         where sh.id = new.FK_shoes_id;
#     end if;
# end;


-- *************************************************************
-- update shoes table after shopping or cancel
-- *************************************************************

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

-- *************************************************************
-- update table NoStock
-- *************************************************************
drop trigger if exists update_no_stock;
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

-- *************************************************************

-- *************************************************************