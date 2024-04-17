create definer = root@localhost view warehouse_manager.vw_accounts_employees as
select `warehouse_manager`.`accounts`.`Acc_id`         AS `Acc_id`,
       `warehouse_manager`.`accounts`.`User_name`      AS `User_name`,
       `warehouse_manager`.`accounts`.`Password`       AS `Password`,
       `warehouse_manager`.`accounts`.`Permission`     AS `Permission`,
       `warehouse_manager`.`accounts`.`Emp_id`         AS `Emp_Id`,
       `warehouse_manager`.`accounts`.`Acc_status`     AS `Acc_status`,
       `warehouse_manager`.`employees`.`Emp_Name`      AS `Emp_name`,
       `warehouse_manager`.`employees`.`Birth_Of_Date` AS `Birth_of_date`,
       `warehouse_manager`.`employees`.`Email`         AS `Email`,
       `warehouse_manager`.`employees`.`Phone`         AS `Phone`,
       `warehouse_manager`.`employees`.`Address`       AS `Address`,
       `warehouse_manager`.`employees`.`Emp_Status`    AS `Emp_status`
from (`warehouse_manager`.`accounts` join `warehouse_manager`.`employees`
      on ((`warehouse_manager`.`accounts`.`Emp_id` = `warehouse_manager`.`employees`.`Emp_Id`)));

