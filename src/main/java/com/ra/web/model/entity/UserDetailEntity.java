package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "acc_detail", schema = "warehouse_manager")
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Basic
    @Column(name = "Acc_Id")
    private Integer accId;
    @Basic
    @Column(name = "User_Id")
    private String userId;
    @Basic
    @Column(name = "User_Name")
    private String userName;
    @Basic
    @Column(name = "Birth_Of_Date")
    private Date birthOfDate;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Phone")
    private String phone;
    @Basic
    @Column(name = "Address")
    private String address;
    @Basic
    @Column(name = "User_Status")
    private Short userStatus;
}
