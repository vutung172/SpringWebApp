create table warehouse_manager.role
(
    Role_Id int          not null
        primary key,
    name    varchar(255) null,
    constraint name
        unique (name)
);

