create definer = root@localhost view warehouse_manager.vw_billdetails_products as
select `warehouse_manager`.`bill_details`.`Bill_Id`        AS `Bill_Id`,
       `warehouse_manager`.`bill_details`.`Bill_Detail_Id` AS `Bill_Detail_Id`,
       `warehouse_manager`.`bill_details`.`Product_Id`     AS `Product_ID`,
       `warehouse_manager`.`bill_details`.`Quantity`       AS `Quantity`,
       `warehouse_manager`.`bill_details`.`Price`          AS `Price`,
       `warehouse_manager`.`products`.`Product_Name`       AS `Product_Name`,
       `warehouse_manager`.`products`.`Manufacturer`       AS `Manufacturer`,
       `warehouse_manager`.`products`.`Created`            AS `Created`,
       `warehouse_manager`.`products`.`Batch`              AS `Batch`,
       `warehouse_manager`.`products`.`Quantity`           AS `Total_Quantity`,
       `warehouse_manager`.`products`.`Product_Status`     AS `Product_Status`
from (`warehouse_manager`.`bill_details` join `warehouse_manager`.`products`
      on ((`warehouse_manager`.`bill_details`.`Product_Id` = `warehouse_manager`.`products`.`Product_Id`)));

