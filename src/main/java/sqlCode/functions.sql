

-- ***********************************************************
-- updates the stock based on status.
-- ************************************************************

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

-- ***********************************************************

-- ***********************************************************

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

-- ***********************************************************
-- Skapa en funktion som tar ett produktId som parameter och returnerar medelbetyget för den
-- produkten. Om du inte har sifferbetyg sedan innan, lägg till dessa, så att en siffra motsvarar ett av de
-- skriftliga betygsvärdena.
-- ***********************************************************

DROP function IF EXISTS getShoesAverageGrade;

DELIMITER $$
CREATE FUNCTION getShoesAverageGrade (shoesId int)
    RETURNS double
    reads sql data
BEGIN
    DECLARE avrageGrade DECIMAL(10,2);
    select avg(grade) into avrageGrade from surveys where FK_shoes_id = shoesId;
    RETURN avrageGrade;
END $$

DELIMITER ;

select getShoesAverageGrade(10); -- 4,5


-- ***********************************************************

-- ***********************************************************