CREATE DATABASE IF NOT EXISTS warehouse;

USE warehouse;

CREATE TABLE accounts(
    Id int primary key auto_increment COMMENT '口座ID',
    User_Name varchar(50) not null unique COMMENT '登録名前',
    Password varchar(100) not null COMMENT 'パスワード',
    Acc_Status bit default 1 COMMENT '口座状態'
);
