package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "products", schema = "warehouse_manager")
public class ProductEntity {
    @Id
    @Column(name = "Product_Id")
    private String productId;
    @Basic
    @Column(name = "Product_Name")
    private String productName;
    @Basic
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Basic
    @Column(name = "Created")
    private Date created;
    @Basic
    @Column(name = "Batch")
    private short batch;
    @Basic
    @Column(name = "Quantity")
    private int quantity;
    @Basic
    @Column(name = "Product_Status")
    private Boolean productStatus;
}
