DROP EVENT  auto_cancel;
delimiter //
create event auto_cancel ON SCHEDULE EVERY 1 MINUTE
    STARTS CURRENT_TIMESTAMP
    do
    if (select count(status) from order_line_item where status = 2  > 0)
    then update order_line_item set status = 4 where status = 2;
    end if//
delimiter ;

SHOW PROCESSLIST;