create table warehouse_manager.bill_details
(
    Bill_Detail_Id bigint auto_increment
        primary key,
    Bill_Id        bigint  not null,
    Product_Id     char(5) not null,
    Quantity       int     not null,
    Price          float   not null,
    constraint bill_details_ibfk_1
        foreign key (Bill_Id) references warehouse_manager.bills (Bill_id),
    constraint bill_details_ibfk_2
        foreign key (Product_Id) references warehouse_manager.products (Product_Id),
    check (`Quantity` > 0),
    check (`Price` > 0)
);

create index Bill_Id
    on warehouse_manager.bill_details (Bill_Id);

create index Product_Id
    on warehouse_manager.bill_details (Product_Id);

