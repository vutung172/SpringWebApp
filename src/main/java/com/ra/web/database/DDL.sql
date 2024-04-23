CREATE DATABASE IF NOT EXISTS ecommerce;

USE ecommerce;

CREATE TABLE accounts(
    User_Id bigint PRIMARY KEY auto_increment COMMENT '口座ID',
    User_Name varchar(50) not null unique COMMENT '登録名前',
    Password varchar(100) not null COMMENT 'パスワード',
    Acc_Status bit default 1 COMMENT '口座状態',
    Created_at Date COMMENT '作成日付',
    Update_at Date COMMENT '更新日付',
                     abc enum('')
);

DROP TABLE accounts;

DELIMITER //
CREATE TRIGGER format_create_date_account
BEFORE INSERT ON accounts FOR EACH ROW
BEGIN
SET NEW.Created_at = STR_TO_DATE(DATE_FORMAT(NOW(),'%d/%c/%Y'),'%d/%c/%Y');
END;
//DELIMITER ;

DROP TRIGGER format_create_date_account;

INSERT INTO accounts(
            USER_NAME,
            PASSWORD
) VALUE (
        'tung',
        '12345'
);
SELECT * FROM accounts;