create definer = root@localhost view warehouse_manager.vw_bills_billdetails as
select `warehouse_manager`.`bills`.`Bill_id`               AS `Bill_id`,
       `warehouse_manager`.`bills`.`Bill_Code`             AS `Bill_Code`,
       `warehouse_manager`.`bills`.`Bill_Type`             AS `Bill_Type`,
       `warehouse_manager`.`bills`.`Emp_id_created`        AS `Emp_id_created`,
       `warehouse_manager`.`bills`.`Created`               AS `Created`,
       `warehouse_manager`.`bills`.`Emp_id_auth`           AS `Emp_id_auth`,
       `warehouse_manager`.`bills`.`Auth_date`             AS `Auth_date`,
       `warehouse_manager`.`bills`.`Bill_Status`           AS `Bill_Status`,
       `warehouse_manager`.`bill_details`.`Bill_Detail_Id` AS `Bill_Detail_Id`,
       `warehouse_manager`.`bill_details`.`Product_Id`     AS `Product_Id`,
       `warehouse_manager`.`bill_details`.`Quantity`       AS `Quantity`,
       `warehouse_manager`.`bill_details`.`Price`          AS `Price`
from (`warehouse_manager`.`bills` join `warehouse_manager`.`bill_details`
      on ((`warehouse_manager`.`bills`.`Bill_id` = `warehouse_manager`.`bill_details`.`Bill_Id`)));

