-- ***********************************************************

-- ***********************************************************

DROP EVENT  auto_cancel;
delimiter //
use shoes_enum;
create event auto_cancel ON SCHEDULE EVERY 2 MINUTE
    STARTS CURRENT_TIMESTAMP
    do
    call shoes_enum.help_auto_cancel();
delimiter ;
SHOW PROCESSLIST;
-- ***********************************************************

-- ***********************************************************