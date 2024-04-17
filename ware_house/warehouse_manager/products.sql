create table warehouse_manager.products
(
    Product_Id     char(5)                      not null
        primary key,
    Product_Name   varchar(150) charset utf8mb3 not null,
    Manufacturer   varchar(200) charset utf8mb3 not null,
    Created        date default (curdate())     null,
    Batch          smallint                     not null,
    Quantity       int  default (0)             not null,
    Product_Status bit  default (1)             null,
    constraint Product_Name
        unique (Product_Name)
);

