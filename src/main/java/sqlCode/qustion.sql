
-- ------------------- 1 ----------------------------------------------------------
-- Lista antalet produkter per kategori. Listningen ska innehålla kategori-namn och antalet produkter.

select c.name category, count(sh.id) as sort_of_shoes,sum(quantity)
from shoes_category sc
         join shoes sh
              on sh.id =  sc.FK_shoes_id
         join category c
              on c.id = sc.Fk_category_id
group by category ;


select c.name category, sum(quantity)
from shoes_category sc
         join shoes sh
              on sh.id =  sc.FK_shoes_id
         join category c
              on c.id = sc.Fk_category_id
group by category ;


-- ------------------- 2 ----------------------------------------------------------
-- Skapa en kundlista med den totala summan pengar som varje kund har handlat för. Kundens för- och efternamn,
-- samt det totala värdet som varje person har shoppats för, skall visas.

SELECT
    cust.name AS customer_name,
    SUM(sh.price * oi.quantity) AS Total_purchase_amount
FROM
    order_line_item oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    customer cust ON ord.FK_customer_id = cust.id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id
GROUP BY cust.name
order by Total_purchase_amount desc ;

-- Detalj -----
SELECT
    ord.id,
    cust.name AS customer_name,
    sh.id,
    sh.price * oi.quantity AS Total_purchase_amount
FROM
    order_line_item oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    customer cust ON ord.FK_customer_id = cust.id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id;

-- ------------------- 3 ----------------------------------------------------------
-- Vilka kunder har köpt svarta sandaler i storlek 38 av märket Ecco? Lista deras namn.

SELECT
    cust.name AS bought_ECCO_39_white
FROM
    order_line_item order_item
        JOIN
    orders ord ON ord.id = order_item.FK_order_id
        JOIN
    customer cust ON ord.FK_customer_id = cust.id
        JOIN
    shoes sh ON sh.id = order_item.FK_shoes_id
        JOIN
    brand br ON br.id = sh.FK_brand_id
WHERE
        color = 'white' AND size = 39
  AND br.name = 'Ecco';

-- ------------------- 4 ----------------------------------------------------------
--  Skriv ut en lista på det totala beställningsvärdet per ort där beställningsvärdet är större än 1000 kr.
--  Ortnamn och värde ska visas.
--  (det måste finnas orter i databasen där det har handlats för mindre än 1000 kr för att visa att frågan är korrekt formulerad)

SELECT
    cust.address AS City,
    SUM(sh.price * oi.quantity) AS Sold_amount
FROM
    order_line_item AS oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    customer cust ON ord.FK_customer_id = cust.id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id
GROUP BY City
HAVING Sold_amount > 10000;

-- ------------------- 5 ---------------------------------------------------------
--  Skapa en topp-5 lista av de mest sålda produkterna.

SELECT
    shoes_id,
    br.name AS brand,
    sh.size AS size,
    sh.color AS color,
    summed.quantity AS quantity
FROM
    (SELECT
         FK_shoes_id AS shoes_id, SUM(quantity) AS quantity
     FROM
         order_line_item
     GROUP BY FK_shoes_id) AS summed
        JOIN
    shoes sh ON sh.id = summed.shoes_id
        JOIN
    brand br ON br.id = sh.FK_brand_id
GROUP BY shoes_id
ORDER BY quantity DESC
LIMIT 5;

-- ------------------- 6 ---------------------------------------------------------
-- Vilken månad hade du den största försäljningen?
-- (det måste finnas data som anger försäljning för mer än en månad i databasen för att visa att frågan är korrekt formulerad)

-- Det här visar en månad i alla år som finns
with total_price_as_MOUNTH as(
    SELECT
        monthname(ord.order_date) order_date,
        SUM(sh.price * oi.quantity) total_price

    FROM
        order_line_item oi
            JOIN
        orders ord ON ord.id = oi.FK_order_id
            JOIN
        shoes sh ON oi.FK_shoes_id = sh.id
    GROUP BY order_date
)
select order_date as month_of_the_biggest_sale, Sum(total_price) as amount_of_sale
from total_price_as_MOUNTH
group by order_date
limit 1;



-- Det här visar mer detalier  och månaden i en viss år .
SELECT  order_month, sum(total_price)
FROM
    ( SELECT
          DATE_FORMAT(ord.order_date ,'%Y-%m') order_month,
          sh.price * oi.quantity total_price
      FROM
          order_line_item oi
              JOIN
          orders ord ON ord.id = oi.FK_order_id
              JOIN
          shoes sh ON oi.FK_shoes_id = sh.id ) AS year_month_price
GROUP BY order_month
ORDER BY sum(total_price) DESC
limit 1;


-- ---------Detalj---------------------------
-- year_month_price_Table(ORDER BY order_date, total_price;)
SELECT
    DATE_FORMAT(ord.order_date ,'%Y-') order_year,
    DATE_FORMAT(ord.order_date ,'%Y-%m') order_month,
    ord.order_date,
    sh.price * oi.quantity total_price

FROM
    order_line_item oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id
ORDER BY order_date, total_price;

-- oeder_date total_price------------------
SELECT
    ord.order_date,
    sh.price * oi.quantity total_price

FROM
    order_line_item oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id
ORDER BY ord.order_date, total_price;

-- varje datum och varje choseID price
SELECT
    ord.id order_ID,
    ord.order_date,
    sh.id AS shose_ID,
    sh.price,
    oi.quantity,
    (sh.price * oi.quantity) AS TotalPrice_per_Shose
FROM
    order_line_item oi
        JOIN
    orders ord ON ord.id = oi.FK_order_id
        JOIN
    shoes sh ON oi.FK_shoes_id = sh.id
ORDER BY ord.order_date , TotalPrice_per_Shose;

