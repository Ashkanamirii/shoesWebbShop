-- Skapa en funktion som tar ett produktId som parameter och returnerar medelbetyget för den
-- produkten. Om du inte har sifferbetyg sedan innan, lägg till dessa, så att en siffra motsvarar ett av de
-- skriftliga betygsvärdena.


USE shoes_enum;
DROP function IF EXISTS getShoesAverageGrade;

DELIMITER $$
CREATE FUNCTION getShoesAverageGrade (shoesId int)
    RETURNS double
    reads sql data
BEGIN
    DECLARE avrageGrade DECIMAL(10,2);
    select avg(grade) into avrageGrade from surveys where FK_shoes_id = shoesId;
    RETURN avrageGrade;
END$$

DELIMITER ;

select getShoesAverageGrade(10); -- 4,5


-- Skapa en vy som visar medelbetyget i siffror och i text för samtliga produkter (om en produkt inte
-- har fått något betyg så skall den ändå visas, med null eller tomt värde, i medelbetyg).
drop view if exists product_average_rate;
create view product_average_rate as
with rate as(
    SELECT
        sh.id as shoes_ID,
        count(*) as count,
        b.name AS shoes_name,
        sh.shoes_number as shoesNumber,
        cast(avg(s.grade) as decimal(10,2)) AS Average_Rate
    FROM
        shoes sh
            LEFT JOIN
        surveys s ON sh.id = s.FK_shoes_id
            JOIN
        brand b ON b.id = sh.FK_brand_id
    GROUP BY sh.shoes_number
)
select shoes_ID , count , shoes_name , shoesNumber , Average_Rate,
       case when Average_Rate >= 4.70 then 'FANTASTIC'
            when Average_Rate >= 4 then 'VERY GOOD'
            when Average_Rate >= 3 then 'GOOD'
            when Average_Rate >= 2 then 'GOODISH'
            when Average_Rate >= 1 then 'BAD'
           end as 'Avrage Rate'
from rate
group by shoes_ID
order by  average_rate DESC;


select * from product_average_rate;



-- Skapa en stored procedure ”Rate” som lägger till ett betyg och en kommentar på en specifik produkt
-- för en specifik kund


DROP procedure IF EXISTS rate;

DELIMITER $$
CREATE PROCEDURE rate (
    IN customerId INT, IN shoesId INT,
    IN rate INT, IN surveysComment varchar(255)
)
BEGIN
    INSERT INTO surveys(comment,grade, FK_shoes_id, FK_customer_id)
    VALUES
    (surveysComment, rate, shoesId, customerId);
END$$

DELIMITER ;

call rate(1,9,3,"it's so so");
call rate(1,3,1,"AWFUL");
call rate(4,3,5,"BEST IN THE WORLD");
call rate(6,3,1,"I hate these shoes");