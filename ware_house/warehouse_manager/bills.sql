create table warehouse_manager.bills
(
    Bill_id        bigint auto_increment
        primary key,
    Bill_Code      varchar(10)                  not null,
    Bill_Type      bit                          not null,
    Emp_id_created char(5)                      not null,
    Created        date     default (curdate()) null,
    Emp_id_auth    char(5)                      not null,
    Auth_date      date     default (curdate()) null,
    Bill_Status    smallint default (0)         not null
);

