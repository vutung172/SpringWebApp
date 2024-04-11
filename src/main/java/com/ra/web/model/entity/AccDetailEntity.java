package com.ra.web.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acc_detail", schema = "warehouse_manager")
public class AccDetailEntity {
    @Id
    @Column(name = "User_Id", nullable = false, length = 5)
    private String userId;
    @Basic
    @Column(name = "User_Name", nullable = true, length = 100)
    private String userName;
    @Basic
    @Column(name = "Birth_Of_Date", nullable = true)
    private Date birthOfDate;
    @Basic
    @Column(name = "Email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "Phone", nullable = false, length = 100)
    private String phone;
    @Basic
    @Column(name = "Address", nullable = false, length = -1)
    private String address;
    @Basic
    @Column(name = "User_Status", nullable = false)
    private short userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccDetailEntity that = (AccDetailEntity) o;
        return userStatus == that.userStatus && Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(birthOfDate, that.birthOfDate) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, birthOfDate, email, phone, address, userStatus);
    }
}
