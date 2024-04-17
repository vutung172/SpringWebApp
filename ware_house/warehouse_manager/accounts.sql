create table warehouse_manager.accounts
(
    Acc_id     int auto_increment
        primary key,
    User_name  varchar(30)     not null,
    Password   varchar(30)     not null,
    Permission bit default (1) null,
    Acc_status bit default (1) null,
    User_id    varchar(255)    null,
    Email      varchar(255)    null,
    constraint User_name
        unique (User_name)
);

