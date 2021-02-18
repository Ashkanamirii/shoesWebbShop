-- skapas alla tabeller med främmande nycklar och används av de olika varianter av referens-integritet
drop database if exists shoesDB;
create database shoesDB;
use shoesDB;
-- SET SQL_SAFE_UPDATES = 0;

create table brand
(

    id      int         not null auto_increment primary key,
    name    varchar(50) not null UNIQUE,
    created timestamp DEFAULT CURRENT_TIMESTAMP,
    updated timestamp ON UPDATE CURRENT_TIMESTAMP

);


create table category
(

    id      int         not null auto_increment primary key,
    name    varchar(50) not null,
    created timestamp DEFAULT CURRENT_TIMESTAMP,
    updated timestamp ON UPDATE CURRENT_TIMESTAMP

);

create table customer
(

    id      int          not null auto_increment primary key,
    name    varchar(50)  not null,
    phone   varchar(10),
    address varchar(255) not null,
    country varchar(50)  not null,
    email   varchar(255) DEFAULT NULL,
    pswd    varchar(255) DEFAULT NULL,
    UNIQUE KEY email (email),
    created timestamp    DEFAULT CURRENT_TIMESTAMP,
    updated timestamp ON UPDATE CURRENT_TIMESTAMP

);

create table shoes
(

    id           int            not null auto_increment primary key,
    size         int            not null,
    shoes_number int            not null UNIQUE,
    FK_brand_id  int            not null,
    color        varchar(15)    not null,
    price        decimal(11, 2) not null,
-- När nyckeln till en post i Brand-tabellen uppdateras vill vi också
-- uppdatera FK_brand_id-värdet i Shoes-tabellen.
-- om en brand uppdaterats då kommer att uppdatera också i shoes tabellen.
    foreign key (FK_brand_id) references brand (id) on update cascade,
    created      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated      timestamp ON UPDATE CURRENT_TIMESTAMP


);

create table shoes_category
(

    FK_category_id int not null,
    FK_shoes_id    int not null,
    primary key (FK_category_id, FK_shoes_id),-- undvika att man gör dubbel lagring

-- När en post i Shoes-tabellen tas bort vill vi att alla Shoes-poster från
-- det märket också ska tas bort ur Shoes_category-tabellen
-- men om en category updaterats då blir också updaterats posten här.
    foreign key (FK_category_id) references category (id) on update cascade,
    foreign key (FK_shoes_id) references shoes (id) on delete cascade on update cascade,
    created        timestamp DEFAULT CURRENT_TIMESTAMP,
    updated        timestamp ON UPDATE CURRENT_TIMESTAMP

);

create table orders
(

    id             int not null auto_increment primary key,
    FK_customer_id int,
    created        timestamp    DEFAULT current_timestamp,
    orders_comment varchar(255) DEFAULT 'NI ÄR FANTASTIKA',
    order_date     date  not null ,
    foreign key (FK_customer_id) references customer (id) on delete set null on update cascade,
    updated        timestamp ON UPDATE CURRENT_TIMESTAMP

);

create table no_stock
(
    id          int not null auto_increment primary key,
    FK_shoes_id int not null,
    foreign key (FK_shoes_id) references shoes (id) on delete cascade on update cascade,
    end_date    timestamp default current_timestamp on update current_timestamp,
    created     timestamp DEFAULT CURRENT_TIMESTAMP,
    updated     timestamp ON UPDATE CURRENT_TIMESTAMP
);

create table order_line_item
(
    id          int                                                                not null auto_increment primary key,
    FK_shoes_id int                                                                not null,
    FK_order_id int                                                                not null,
    quantity    int                                                                not null,
    status      ENUM ('CONFIRMED', 'PAYING' , 'CANCEL' , 'AUTO_CANCEL','RETURNED') not null DEFAULT 'CONFIRMED',
    foreign key (FK_shoes_id) references shoes (id) on update cascade,
    foreign key (FK_order_id) references orders (id) on update cascade,
    created     timestamp                                                                   DEFAULT CURRENT_TIMESTAMP,
    unique (FK_shoes_id, FK_order_id, status),
    updated     timestamp ON UPDATE CURRENT_TIMESTAMP
);

create table grade
(

    id      int         not null auto_increment primary key,
    name    varchar(50) not null,
    created timestamp DEFAULT CURRENT_TIMESTAMP,
    updated timestamp ON UPDATE CURRENT_TIMESTAMP

);

create table surveys
(

    id             int not null auto_increment,
    comment        varchar(255) DEFAULT 'JAG ÄLSKAR MINA SKOR',
    FK_grade_id    int not null,
    issue_date     timestamp    DEFAULT CURRENT_TIMESTAMP,
    FK_shoes_id    int not null,
    FK_customer_id int not null,
    primary key (id),
    foreign key (FK_grade_id) references grade (id) on update cascade,
    foreign key (FK_shoes_id) references shoes (id) on delete cascade,
    foreign key (FK_customer_id) references customer (id) on delete cascade,
    created        timestamp    DEFAULT CURRENT_TIMESTAMP,
    updated        timestamp ON UPDATE CURRENT_TIMESTAMP
);

insert into brand(name)
values ('Nike'),
       ('Adidas'),
       ('Ecco'),
       ('Reebok'),
       ('Vans'),
       ('Puma'),
       ('Skechers'),
       ('Fila');

insert into category(name)
values ('Sport'),
       ('Kids'),
       ('Trainers'),
       ('Boots'),
       ('Walking');


insert into customer(name, phone, address, country, email, pswd)
values ('Ashkan', '0739975150', 'Stockholm ', 'Sweden', 'Ashkan@gmail.com', MD5('Ashkan1234')),

       ('Sigrun', '0739975151', 'Tehran', 'Iran', 'Sigrun@gmail.com', MD5('Sigrun1234')),

       ('Peter', '0739975152', 'Berlin', 'Germany ', 'Peter@gmail.com', MD5('Peter1234')),

       ('Hodei', '0739975153', 'Kista', 'Sweden', 'Hodei@gmail.com', MD5('Hodei1234')),

       ('Oscar', '0739975154', 'Washintgton', 'USA', 'Oscar@gmail.com', MD5('Oscar1234')),

       ('Salem', '0739975155', 'London', 'England', 'Salem@gmail.com', MD5('Salem1234')),

       ('Sara', '0739975156', 'New Delhi', 'India', 'Sara@gmail.com', MD5('Sara1234')),

       ('Lili', '0739975157', 'Sollentuna', 'Sweden', 'Lili@gmail.com', MD5('Lili1234'));

insert into shoes(size, FK_brand_id, shoes_number, color, price)
values (40, 1, 112, 'red', 2200),
       (41, 2, 115, 'blue', 2400),
       (43, 2, 259, 'yellow', 2500),
       (38, 4, 358, 'red', 3200),
       (45, 6, 751, 'black', 1700),
       (42, 7, 678, 'black', 1000),
       (40, 1, 123, 'orange', 200),
       (39, 3, 321, 'white', 5000),
       (43, 8, 233, 'yellow', 3000),
       (38, 5, 145, 'black', 2500);

insert into shoes_category(FK_category_id, FK_shoes_id)
values (1, 1),
       (3, 1),
       (2, 1),
       (3, 2),
       (5, 8),
       (2, 2),
       (4, 1),
       (3, 8);

insert into orders(FK_customer_id, order_date)
values (1, '2020-01-15'),
       (2, '2020-01-17'),
       (3, '2020-01-18'),
       (7, '2021-01-03'),
       (8, '2020-01-29'),
       (4, '2020-01-06'),
       (1, '2020-03-17'),
       (5, '2020-01-03'),
       (1, '2020-05-06'),
       (1, '2020-01-05'),
       (6, '2020-05-03'),
       (1, '2021-01-25'),
       (1, '2020-03-09'),
       (5, '2020-01-25'),
       (5, '2020-01-14'),
       (2, '2020-08-25'),
       (2, '2020-01-03');

insert into order_line_item(FK_shoes_id, FK_order_id, quantity, status)
values (2, 1, 2, 'CONFIRMED'),
       (9, 1, 1, 'CONFIRMED'),
       (10, 1, 1, 'CONFIRMED'),
       (7, 2, 2, 'CONFIRMED'),
       (2, 2, 2, 'CONFIRMED'),
       (8, 2, 2, 'CONFIRMED')
        ,
       (9, 2, 2, 'CONFIRMED'),
       (7, 3, 2, 'CONFIRMED'),
       (4, 3, 2, 'CONFIRMED'),
       (7, 4, 3, 'CONFIRMED'),
       (3, 5, 2, 'CONFIRMED'),
       (2, 6, 3, 'CONFIRMED'),
       (5, 6, 1, 'CONFIRMED'),
       (8, 7, 2, 'CONFIRMED'),
       (6, 8, 2, 'CONFIRMED'),
       (4, 9, 2, 'CONFIRMED'),
       (2, 10, 4, 'CONFIRMED'),
       (2, 11, 4, 'CONFIRMED'),
       (5, 12, 1, 'CONFIRMED'),
       (3, 13, 2, 'CONFIRMED'),
       (2, 14, 1, 'CONFIRMED'),
       (10, 15, 4, 'CONFIRMED'),
       (9, 16, 1, 'CONFIRMED'),
       (7, 17, 2, 1),
       (7, 17, 2, 5);


insert into grade(name)
values ('MISSNÖJD'),
       ('GANSKA NÖJD'),
       ('NÖJD'),
       ('MYCKET NÖJD'),
       ('FANTASTISK');

insert into surveys (FK_grade_id, FK_customer_id, FK_shoes_id)
values (5, 1, 9),
       (4, 1, 10),
       (2, 1, 2),
       (5, 5, 10),
       (4, 4, 2),
       (3, 6, 2),
       (1, 5, 2),
       (5, 2, 2);

-- create index IX_brandName on brand(name);   Det är inte rimlig för att vi har inte så många brander i varkligheten.
create index IX_customerName on customer (name);


-- Sigruns krav
-- ER-modellen ska vara på 3NF. Om du medvetet väljer att frångå 3NF för någon eller några
-- tabeller ska detta motiveras med en bra anledning varför, i en kommentar.
-- vår anledning
-- Gäller om 3NF måste vi dela upp customer och country till 2 separata tabell (country -> address)
-- DOCK behöver vi  inte göra det för att det är onödigt(med tänk på databas krav) för vår databas dessutom vi betjänt inte oss att ha två separata tabell.

ALTER TABLE shoes
    ADD COLUMN quantity INT NOT NULL DEFAULT 0 AFTER updated;


UPDATE shoes
SET quantity = '120'
WHERE id = '1';
UPDATE shoes
SET quantity = '150'
WHERE id = '2';
UPDATE shoes
SET quantity = '180'
WHERE id = '3';
UPDATE shoes
SET quantity = '20'
WHERE id = '4';
UPDATE shoes
SET quantity = '45'
WHERE id = '5';
UPDATE shoes
SET quantity = '150'
WHERE id = '6';
UPDATE shoes
SET quantity = '100'
WHERE id = '7';
UPDATE shoes
SET quantity = '140'
WHERE id = '8';
UPDATE shoes
SET quantity = '120'
WHERE id = '9';
UPDATE shoes
SET quantity = '140'
WHERE id = '10';
