package com.ra.web.model.entity;

import com.ra.web.model.entity.accounts.AccEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_details", schema = "warehouse_manager")
public class EmployeeDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Basic
    @Column(name = "Acc_Id")
    private Integer accId;
    @Basic
    @Column(name = "Employee_Id")
    private String employeeId;
    @Basic
    @Column(name = "Employee_Name")
    private String employeeName;
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

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "accId", referencedColumnName = "Acc_Id")
    private AccEntity accByEmp;
}
