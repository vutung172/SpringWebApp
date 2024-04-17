create table warehouse_manager.user_details
(
    Acc_Id        int                          not null,
    Id            int auto_increment
        primary key,
    User_Id       varchar(255)                 null,
    User_Name     varchar(100) charset utf8mb3 not null,
    Birth_Of_Date date                         null,
    Email         varchar(100)                 not null,
    Phone         varchar(100)                 not null,
    Address       text                         not null,
    User_Status   smallint                     null,
    constraint Acc_Id
        unique (Acc_Id),
    constraint user_details_accounts_Acc_id_fk
        foreign key (Acc_Id) references warehouse_manager.accounts (Acc_id)
);

