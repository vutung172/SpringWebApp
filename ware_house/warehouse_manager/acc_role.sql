create table warehouse_manager.acc_role
(
    Acc_Id   int not null,
    Role_Id  int not null,
    accById  int null,
    roleById int null,
    primary key (Acc_Id, Role_Id),
    constraint FKgptu5cnu3oermp26stxsuy9ow
        foreign key (roleById) references warehouse_manager.role (Role_Id),
    constraint FKiuinmx6qwcxas50u3pi0cdawq
        foreign key (accById) references warehouse_manager.accounts (Acc_id),
    constraint acc_role_ibfk_1
        foreign key (Acc_Id) references warehouse_manager.accounts (Acc_id),
    constraint acc_role_ibfk_2
        foreign key (Role_Id) references warehouse_manager.role (Role_Id)
);

create index roleId
    on warehouse_manager.acc_role (Role_Id);

