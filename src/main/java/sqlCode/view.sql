-- *************************************************************
-- Skapa en vy som visar medelbetyget i siffror och i text för samtliga produkter (om en produkt inte
-- har fått något betyg så skall den ändå visas, med null eller tomt värde, i medelbetyg).
-- *************************************************************
drop view if exists product_average_rate;
create view product_average_rate as
with rate as(
    SELECT
        sh.id as shoes_ID,
        count(*) as Number_Of_Rating,
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
select shoes_name , shoesNumber , Number_Of_Rating ,  Average_Rate,
       case when Average_Rate >= 4.70 then 'FANTASTIC'
            when Average_Rate >= 4 then 'VERY GOOD'
            when Average_Rate >= 3 then 'GOOD'
            when Average_Rate >= 2 then 'GOODISH'
            when Average_Rate >= 1 then 'BAD'
           end as 'Rate'
from rate
group by shoes_ID
order by  average_rate DESC;

-- *************************************************************
select * from product_average_rate;
-- *************************************************************