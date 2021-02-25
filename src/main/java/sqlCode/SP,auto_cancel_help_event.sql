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



drop  trigger on_status_update_autoCancel;
-- Det kör efter SP
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
end //
delimiter ;